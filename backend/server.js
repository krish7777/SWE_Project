const express = require('express')
const cors = require('cors')
const bodyParser = require('body-parser');
const mongoose = require('mongoose');

const authRoutes = require('./routes/auth')


const app = express();
const PORT = process.env.PORT || 8000
app.use(bodyParser.json({ limit: '50mb' }));
app.use(bodyParser.urlencoded({ limit: '50mb', extended: true }));
app.use(cors())



app.get('/test', (req, res) => {
    console.log(req.body)
    console.log("test route hit")
    res.json({ "yes": "no" })
})

app.use('/auth', authRoutes)

app.use((error, req, res, next) => {// Error Handling
    console.log(error);
    const status = error.statusCode || 500;
    const message = error.message;
    const data = error.data;
    res.status(status).json({ message: message, data: data });
});


const MONGO_URI = "mongodb://127.0.0.1:27017/swe_project"

mongoose.connect(MONGO_URI, {
    useNewUrlParser: true,
    useUnifiedTopology: true
}).then(res => {
    app.listen(PORT, () => {
        console.log('server started')
    })
}).catch(err => {
    console.log("error with connecting db")
})