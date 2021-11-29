const mongoose=require('mongoose');
const bcrypt=require('bcrypt');
const saltRounds=10;
const UserSchema=new mongoose.Schema({
    username:{type: String,required:true,unique:true },
    password: { type: String, required:true}
});
UserSchema.pre('save',function(next){
    console.log('Entra a metodo Save');
    if(this.isNew || this.isModified('password')){
       const document =this;
        console.log('Entra a if save');
       bcrypt.hash(document.password,saltRounds,(err,hashedPassword)=>{
           if(err){
               next(err);
           }
           else{
               document.password=hashedPassword;
               next();
           }
       
        });
    } 
    else {
         next();
    }
});
UserSchema.methods.isCorrectPassword = function(password,callback){
    bcrypt.compare(password,this.password,function(err,same){
        if(err){
            callback(err);
        }
        else{
            callback(err,same);
        }
    });
}
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
module.exports = mongoose.model('user',UserSchema);