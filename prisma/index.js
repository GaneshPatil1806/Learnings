const express = require('express');
const app = express();
const cookieParser = require('cookie-parser');
require('dotenv').config();

app.use(express.json()) // from req body we can use json
app.use(express.urlencoded({extended:true}));
app.use(cookieParser());

const userRouter = require('./routes/user');

app.use('/api',userRouter);

app.get('/',(req,res)=>{
    res.send("hi")
});

app.listen(3000,()=>{
    console.log('Server is up on 3000');
})