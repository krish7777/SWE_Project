const express = require('express')
const cors = require('cors')
const bodyParser = require('body-parser');
const mongoose = require('mongoose');


const app = express();
const PORT = process.env.PORT || 8000
app.use(bodyParser.json({ limit: '50mb' }));
app.use(bodyParser.urlencoded({ limit: '50mb', extended: true }));
app.use(cors());
const MONGO_URI = "mongodb+srv://arpit:arpit@cluster0.lr4ce.mongodb.net/techsite?retryWrites=true&w=majority"
mongoose.connect(MONGO_URI, {
    useNewUrlParser: true,
    useUnifiedTopology: true
}).then(res => {
    app.listen(PORT, () => {
        console.log('server staarted')
    })
}).catch(err => {
    console.log("error with connecting db")
})

const donorRouter = require('./routes/donor');
const organisationRouter = require('./routes/organisation');

app.use('/donor',donorRouter);
app.use('/organisation',organisationRouter);

