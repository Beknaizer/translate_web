document.getElementById("user_div").style.display = "block";

var user = firebase.auth().currentUser;
if (user) {
    // User is signed in.

    if (user != null) {
        var email_id = user.email;
        document.getElementById("user_para").innerHTML = "Welcome User : " + email_id;

    }
} else {
    // No user is signed in.
    console.log("not signed in")
}


function logout(){

    firebase.auth().signOut();
    window.location.replace("http://localhost:8080/");

}