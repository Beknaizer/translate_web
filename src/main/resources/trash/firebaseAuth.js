// console.log("hello");
$(document).ready(function() {

    function login(){
        var userEmail = document.getElementById("email").value;
        var userPass = document.getElementById("password").value;

        firebase.auth().signInWithEmailAndPassword(userEmail, userPass).catch(function(error) {
            // Handle Errors here.
            var errorCode = error.code;
            var errorMessage = error.message;

            window.alert("Error : " + errorMessage);

            // ...
        });
    }

    function logout(){
        firebase.auth().signOut();
    }

    $('#logout').click(function (){
        logout();
    });

    $('#loginBtn').click(function() {
        login();
    })


    firebase.auth().onAuthStateChanged(function(user) {

        if (user) {
            // User is signed in.
            var user = firebase.auth().currentUser;

            if(user != null) {
                console.log(user.uid)
                window.location.replace("https://www.google.com/");
            }


        } else {
            // No user is signed in.
            console.log("not signed in")


        }
    });

    });

