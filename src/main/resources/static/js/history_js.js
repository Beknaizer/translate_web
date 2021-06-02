$('a[data-toggle="list"]').on('shown.bs.tab', function (e) {
    $(e.target).removeClass("light-blue");
    $(e.relatedTarget).addClass("light-blue");
})

function short(s) {
    if (s.length > 7) {
        return s.substring(0, 7) + "...";
    } else {
        return s
    }
}

$(document).ready(function () {
    firebase.auth().onAuthStateChanged(function (user) {
        if (user) {
            // User is signed in.
            document.getElementById("translate").style.display = "block";
            document.getElementById("signIn").style.display = "none";
            document.getElementById("logout").style.display = "block";

            console.log("user signed in")

            jQuery.ajax({
                url: "http://localhost:8080/translateHistory/" + user.email,
                type: "GET",
                contentType: 'application/json; charset=utf-8',
                success: function (resultData) {
                    // alert(resultData[0].textToTranslate);
                    for (var i in resultData) {
                        if (parseInt(i) === 0) {
                            $('#list-tab').append('<a class="list-group-item list-group-item-action active" id="id_' + i + '-list" data-toggle="list" href="#list-' + i + '" role="tab">' + short(resultData[i].textToTranslate) + '</a>')
                            $('#nav-tabContent').append('<div class="tab-pane fade show active" id="list-'+ i +'" role="tabpanel"><span>'+ resultData[i].textToTranslate+'</span><hr><span>'+resultData[i].translatedText+'</span></div>')

                        } else {
                            $('#list-tab').append('<a class="list-group-item list-group-item-action" id="list-prince-list" data-toggle="list" href="#list-' + i + '" role="tab" >' + short(resultData[i].textToTranslate) + '</a>')
                            $('#nav-tabContent').append('<div class="tab-pane fade show" id="list-'+ i +'" role="tabpanel"><span>'+ resultData[i].textToTranslate+'</span><hr><span>'+resultData[i].translatedText+'</span></div>')

                        }
                    }
                },
                error: function (error) {
                    console.log(error);
                    alert("Invalid Input");
                },
                timeout: 120000,
            });
        } else {
            // No user is signed in.
            document.getElementById("translate").style.display = "none";
            document.getElementById("signIn").style.display = "block";
            document.getElementById("logout").style.display = "none";

            console.log("no user signed in")
        }

        $('#logout').click(function () {
            firebase.auth().signOut();
            console.log("signed out")
        })
    });
});