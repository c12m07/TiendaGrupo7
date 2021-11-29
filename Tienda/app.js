const express = require('express');
const path = require('path');
const bodyParser = require('body-parser')
const app = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended:false}));
app.use(express.static(path.join(__dirname,'public')));
const http = require("http");

function ingreso(){

  var u="admininicial";
  var c="admin123456";


  if(document.form.login.value==u && document.form.password.value==c){
  alert ("Bienvenido a Tienda Generica");
  window.location="menu.html";}
  else{
  alert ("Usuario o Contraseña Incorrectos");

}
}

app.get('/find',(req,resp) => { 
    http
    .get("http://localhost:8080/api/tutorials", (resp) => {
      let data = "";
      // A chunk of data has been recieved. Append it with the previously retrieved chunk of data
      resp.on("data", (chunk) => {
        data += chunk;
      });
  
      // when the whole response is received, parse the result and Print it in the console
      resp.on("end", () => {
        console.log(JSON.parse(data));
      });
    })
    .on("error", (err) => {
      console.log("Error: " + err.message);
    });
  });

 
  app.post('/crear',(req,res) => { 
      console.log('body: ',req.body);
    const {title,description} =req.body;
    const data = JSON.stringify({
        description: description,
        title: title,
      });
    
    const options = {
        host: "localhost",
        port: 8080,
        path: "/api/tutorials",
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Content-Length": data.length,
        },
      };
      
      
      
      req = http.request(options, (res) => {
            //status code of the request sent
            console.log("statusCode: ", res.statusCode);
            let result = "";
            // A chunk of data has been recieved. Append it with the previously retrieved chunk of data
            res.on("data", (chunk) => {
              result += chunk;
            });
            //The whole response has been received. Display it into the console.
            res.on("end", () => {
              console.log("Result is: " + result);
            });
          });
          //error if any problem with the request
          req.on("error", (err) => {
            console.log("Error: " + err.message);
          });
          //write data to request body
          req.write(data);
          //to signify the end of the request - even if there is no data being written to the request body.
          req.end();
});



app.listen(3000,() => console.log('Servidor Inicia puerto 3000'));

module.exports=app;