var db = firebase.firestore();


firebase.auth().onAuthStateChanged(function(user) {
  if (user) {
    // User is signed in.
    var user = firebase.auth().currentUser;

    if(user != null){
      console.log(user.uid)

        window.location.href = 'http://localhost:8080/homepage'

    }

  } else {
    // No user is signed in.
    document.getElementById("login_div").style.display = "block";
  }

});

var incorrectPass = false;


function login(){

  var userEmail = document.getElementById("email_field_log").value;
  var userPass = document.getElementById("password_field_log").value;

  firebase.auth().signInWithEmailAndPassword(userEmail, userPass).catch(function(error) {
    // Handle Errors here.
    var errorCode = error.code;
    var errorMessage = error.message;

    window.alert("Error: "+ errorMessage);
    incorrectPass = true;

    // ...
  });

  if(incorrectPass){
    document.getElementById("resetLink").style.display="block";
  }

}

function registration_link(){
  document.getElementById("registration_div").style.display = "block"
  document.getElementById("login_div").style.display = "none"

}

function login_link(){
  document.getElementById("registration_div").style.display = "none"
  document.getElementById("login_div").style.display = "block"
  document.getElementById("reset_pass_div").style.display= "none"
}

function registration(){
  var email = document.getElementById("email_field").value;
  var pass = document.getElementById("password_field").value;
  var firstname = document.getElementById("firstname_field").value;
  var lastname = document.getElementById("lastname_field").value;
  var username = document.getElementById("username_field").value;


   firebase.auth().createUserWithEmailAndPassword(email, pass).then(async function (){

    var id = firebase.auth().currentUser.uid;

    console.log(id,email,pass,firstname,lastname,username);

    var jsonData =  {
        "email":email,
        "id":id,
        "username":username,
        "firstname":firstname,
        "lastname":lastname
    }

     jQuery.ajax({
       url: "http://localhost:8080/registration",
       type: "POST",
       contentType: 'application/json; charset=utf-8',
       data:JSON.stringify(jsonData),
       success: function (resultData) {
         console.log("successfully registered!")
       },
       error: function (error) {
         console.log(error);
       },
       timeout: 120000,
     });


      // var a = await db.collection("User").
      //   add({
      //     uid: id,
      //     email: email,
      //     username: username,
      //     firstname: firstname,
      //     lastname: lastname
      //   }).then(() => {
      //     console.log("Document written  ");
      //   })
      //   .catch((error) => {
      //     console.error("Error adding document: ", error);
      //   });
      //

  }).catch((error) => {
        var errorCode = error.code;
        var errorMessage = error.message;
        window.alert(errorMessage);
        console.log(errorCode, errorMessage);
  });

}




// function ajax_registration(id,email,username,firstname,lastname){
//   jQuery.ajax({
//     url: "http://localhost:8080/registration",
//     type: "GET",
//     contentType: 'application/json; charset=utf-8',
//     data:
//         {
//           "email":email,
//           "id":"id example",
//           "username":username,
//           "firstname":firstname,
//           "lastname":lastname
//         },
//     success: function (resultData) {
//       console.log("successfully registered!")
//     },
//     error: function (error) {
//       console.log(error);
//     },
//     timeout: 120000,
//   });
// }

function reset_pass_link(){

  document.getElementById("registration_div").style.display = "none"
  document.getElementById("login_div").style.display = "none"
  document.getElementById("reset_pass_div").style.display = "block"

}


function resetPass(){

  var emailAddress = document.getElementById("email_field_reset").value;

  firebase.auth().sendPasswordResetEmail(emailAddress).then(function() {
    // Email sent.
    alert("email sent");
    document.getElementById("login_div").style.display = "block"
    document.getElementById("reset_pass_div").style.display = "none"


  }).catch(function(error) {
    // An error happened.
    alert("error")
    console.log("Error:"+error.message)
  });
}
