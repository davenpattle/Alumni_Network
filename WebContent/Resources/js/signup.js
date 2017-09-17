$(document).ready(function() {
     var userId;

     $("#submitprofile").click(function(){
        $.ajax({
        type: "POST",
        url: "http://localhost:8080/AddUserServlet",
        data: {fname: document.getElementById("fname").value,
               lname: document.getElementById("lname").value,
               email: document.getElementById("email").value,
               location:document.getElementById("location").value,
               contact: document.getElementById("contact").value
               },
        success: function(result,status,xhr) {
            window.alert("Your details have been stored successfully.");

            var userprofile = document.getElementById("userprofile");
            userprofile.style = "display: none;"
            var userlogin = document.getElementById("userlogin");
            userlogin.style = "display: block;"

            userId = parseInt(result);
        },
        error: function(xhr,status,error) {
            window.alert(error);
        }
        }
        );
    });

    $("#submitlogin").click(function(){
            $.ajax({
            type: "POST",
            url: "http://localhost:8080/RegisterUserServlet",
            data: {uid: userId,
                   username: document.getElementById("username").value,
                   password: document.getElementById("password1").value,
                   },
            dataType: "text",
            success: function(result,status,xhr) {
            //window.alert(result);
                if(parseInt(result)==0){
                    window.alert("The specified User Name is already taken.");
                }
                else{
                    window.alert("You have been successfully registered!");
                    window.location.href="http://localhost:8080/Website/Login.html";
                }
            },
            error: function(xhr,status,error) {
                window.alert("Oops! There was a problem. Please try again!");
            }
            }
            );
        });
});