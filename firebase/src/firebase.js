// firebase.js
const { initializeApp } = require('firebase/app');
const { getStorage, ref, uploadBytesResumable, getDownloadURL } = require('firebase/storage');
const multer = require('multer');
const express = require('express');
const router = express.Router();
const dotenv = require('dotenv');
dotenv.config();

// Firebase configuration
const firebaseConfig = {
  apiKey: process.env.API_KEY,
  authDomain: process.env.AUTH_DOMAIN,
  projectId: process.env.PROJECT_ID,
  storageBucket: process.env.BUCKET,
  messagingSenderId: process.env.SENDER,
  appId: process.env.APP_ID,
  measurementId: process.env.M_ID
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

// Initialize Cloud Storage and get a reference to the service
const storage = getStorage(app);

// Multer middleware to handle file uploads in memory
const upload = multer({ storage: multer.memoryStorage() });

// Function to get current date and time
const giveCurrentDateTime = () => {
  const today = new Date();
  const date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
  const time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
  const dateTime = date + ' ' + time;
  return dateTime;
};

router.post("/upload", upload.single("file"), async (req, res) => {
    try {
        if (!req.file) {
            return res.status(400).send("No file uploaded.");
        }

        const dateTime = giveCurrentDateTime();
        const storageRef = ref(storage, `files/${req.file.originalname}_${dateTime}`);

        // Create file metadata including the content type
        const metadata = {
            contentType: req.file.mimetype,
        };

        // Upload the file in the bucket storage
        const uploadTask = uploadBytesResumable(storageRef, req.file.buffer, metadata);

        // Monitor upload progress and handle success/error
        uploadTask.on('state_changed',
          (snapshot) => {
            // Observe state change events such as progress, pause, and resume
            const progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
            console.log('Upload is ' + progress + '% done');
          }, 
          (error) => {
            // Handle unsuccessful uploads
            console.error('Upload error:', error);
            console.error('Error details:', error.serverResponse); // Log server response
            res.status(500).send('Error uploading file: ' + error.message);
          }, 
          async () => {
            // Handle successful uploads on complete
            try {
              const downloadURL = await getDownloadURL(uploadTask.snapshot.ref);
              console.log('File successfully uploaded.');
              res.status(200).send({
                message: 'File uploaded to Firebase Storage',
                name: req.file.originalname,
                type: req.file.mimetype,
                downloadURL: downloadURL
              });
            } catch (getURLerror) {
              console.error('Error getting download URL:', getURLerror);
              res.status(500).send('Error getting download URL.');
            }
          }
        );
    } catch (error) {
        console.error('Error uploading file:', error);
        res.status(500).send('Error uploading file.');
    }
});

module.exports = router;
