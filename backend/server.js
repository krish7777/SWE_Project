const express = require('express')
const cors = require('cors')
const bodyParser = require('body-parser');
const mongoose = require('mongoose');
const multer = require('multer')
const fs = require('fs')
const path = require('path')

const authRoutes = require('./routes/auth')


const app = express();
const PORT = process.env.PORT || 8000
app.use(bodyParser.json({ limit: '50mb' }));
app.use(bodyParser.urlencoded({ limit: '50mb', extended: true }));
app.use('/images', express.static(path.join(__dirname, 'images')));
app.use(cors())



app.get('/test', (req, res) => {
    console.log(req)
    console.log(req.get('Authorization'))
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


const MONGO_URI = "mongodb://127.0.0.1:27017/swe_project" // TEST DB
//   const MONGO_URI = "mongodb+srv://arpit:arpit@cluster0.lr4ce.mongodb.net/techsite?retryWrites=true&w=majority" //-- ORIGINAL DB

// const MONGO_URI = "mongodb+srv://arpit:arpit@cluster0.lr4ce.mongodb.net/techsite?retryWrites=true&w=majority"
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

const donorRouter = require('./routes/donor');
const organisationRouter = require('./routes/organisation');


app.use('/donor', donorRouter);
app.use('/organisation', organisationRouter);


const imageStorage = multer.diskStorage({
    destination: function (req, file, cb) {
        console.log("fiel in img", file);
        const reqPath = path.join(__dirname, 'images')
        if (!fs.existsSync(reqPath)) {
            fs.mkdirSync(reqPath, { recursive: true })
        }
        cb(null, reqPath)
    },
    filename: function (req, file, cb) {
        cb(null, Date.now() + '-' + file.originalname)
    }
})

// const imageFilter = (req, file, cb) => {
//     if (file.mimetype === 'image/png' ||
//         file.mimetype === 'image/jpg' ||
//         file.mimetype === 'image/jpeg'
//     ) {
//         cb(null, true);
//     } else {
//         cb(null, false);
//     }
// }

const uploadImage = multer({
    storage: imageStorage,
    limits: {
        fieldSize: 3000000
    },
    // fileFilter: imageFilter
})

app.post('/upload', uploadImage.single('file'),
    (req, res) => {
        console.log("hooorayyy")
        console.log('req.file', req.file)
        res.json({
            "location": `http://192.168.1.10:8000/images/${req.file.filename}`, "originalName": req.file.originalname
        })//PUT IP ADDRESS HERE
    }
)

