const express = require('express') ; // Use ES module import
const dotenv = require('dotenv');
const fileUploadRoutes = require('./firebase'); // Import with ES module syntax

dotenv.config();
const app = express();

const PORT = process.env.PORT || 3000;

app.use(express.json());
app.use('/file', fileUploadRoutes); // Ensure '/file' matches route prefix

app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
