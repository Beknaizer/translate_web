
$(document).ready(function() {

    firebase.auth().onAuthStateChanged(function(user) {
        if (user) {
            // User is signed in.
            document.getElementById("history").style.display = "block";
            document.getElementById("signIn").style.display = "none";
            document.getElementById("logout").style.display = "block";

            console.log("user signed in")
        } else {
            // No user is signed in.
            document.getElementById("history").style.display = "none";
            document.getElementById("signIn").style.display = "block";
            document.getElementById("logout").style.display = "none";

            console.log("no user signed in")
        }
    });

    $('#logout').click(function (){
        firebase.auth().signOut();
        console.log("signed out")
    })

    $('#history').click(function (){
        window.location.href = 'http://localhost:8080/history'
    })

    $('#translateBtn').click(function (){
        var textToTranslate = document.getElementById("textToTranslate").innerText.toString();
        var fromLanguage = document.getElementById("language-selector").value;
        var toLanguage = document.getElementById("language-selector2").value;
        console.log(textToTranslate+" "+fromLanguage+" "+toLanguage);
        var user = firebase.auth().currentUser;
        var userEmail = null;
        if (user) {
            userEmail = user.email;
        } else {
            // No user is signed in.
        }

        jQuery.ajax({
            url: "http://localhost:8080/translate",
            type: "GET",
            contentType: 'application/json; charset=utf-8',
            data:
                {
                    "userEmail":userEmail,
                    "textToTranslate":textToTranslate,
                    "fromLanguage":fromLanguage,
                    "toLanguage":toLanguage
                },
            success: function (resultData) {
                $("#result").html("");
                localStorage.removeItem("wysiwyg");
                $('#result').append('<p>'+resultData+'</p>');
            },
            error: function (error) {
                console.log(error);
                alert("Invalid Input");
            },
            timeout: 120000,
        });

    });

    $(".newPost button[data-func]").click(function () {
    document.execCommand($(this).data("func"), false);
    });

$(".newPost select[data-func]").change(function () {
    var $value = $(this).find(":selected").val();
    document.execCommand($(this).data("func"), false, $value);
});

if (typeof Storage !== "undefined") {
    $(".editor").keypress(function () {
        $(this).find(".saved").detach();
    });
    $(".editor").html(localStorage.getItem("wysiwyg"));

    $('button[data-func="save"]').click(function () {
        $content = $(".editor").html();
        localStorage.setItem("wysiwyg", $content);
        $(".editor")
            .append('<span class="saved"><i class="fa fa-check"></i></span>')
            .fadeIn(function () {
                $(this).find(".saved").fadeOut(500);
            });
    });

    $('button[data-func="clearTranslate"]').click(function () {
        $("#textToTranslate").html("");
        localStorage.removeItem("wysiwyg");
    });

    $('button[data-func="clearResult"]').click(function () {
        $("#result").html("");
        localStorage.removeItem("wysiwyg");
    });


}




//voice recorder
const micContainer = document.getElementsByClassName('mic-container')[0];

micContainer.addEventListener('click', (e)=> {
    let elem = e.target;

    elem.classList.toggle('active');
});


});



