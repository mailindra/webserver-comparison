const express = require('express')
const app = express()
const port = 8080
const host ='0.0.0.0'

app.get('/', (req, res) => {
  res.send('Hello, People')
})

app.listen(port,host, () => {
  console.log(`Example app listening on port ${port}`)
})