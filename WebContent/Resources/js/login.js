var companies;
var institutes;
var expertises;
var jobs;

$(document).ready(function() {
     var userId;
     var userData;
     var userCompany;
     var userInstitute;
     var userExpertise;
     var jobs;

    $("#submitlogin").click(function(){
            $.ajax({
            type: "POST",
            async: false,
            url: "http://localhost:8080/UserAuthenticationServlet",
            data: {username: document.getElementById("username").value,
                   password: document.getElementById("password1").value,
                   },
            dataType: "text",
            success: function(result,status,xhr) {
            //window.alert(result);
                if(parseInt(result)==0){
                    window.alert("UserName/Password specified is wrong");
                }
                else{
                    window.alert("You have been successfully verified!");
                    var data = JSON.parse(result);
                    userData = data;
                    userId = data.userid;

                    var userlogin = document.getElementById("userlogin");
                    userlogin.style = "display: none;";

                    var welcomemsg = document.getElementById("welcomemsg");
                    welcomemsg.innerHTML = "Hello " + data.user_name;

                    var fname = document.getElementById("fname");
                    fname.innerHTML = data.f_name;

                    var lname = document.getElementById("lname");
                    lname.innerHTML = data.l_name;

                    var email = document.getElementById("email");
                    email.innerHTML = data.email;

                    var location = document.getElementById("location");
                    location.innerHTML = data.location;

                    var contact = document.getElementById("contact");
                    contact.innerHTML = data.contact;

                    var userprofile = document.getElementById("userprofile");
                    userprofile.style = "display: block;";

                    var options = document.getElementById("options");
                    options.style= "visibility: visible;"
                }
            },
            error: function(xhr,status,error) {
                window.alert("Oops! There was a problem. Please try again!");
            }
            }
            );
        });

        $("#viewprofilebtn").click(function(){
                        var editprofile = document.getElementById("editprofile");
                        editprofile.style = "display: none;";

                        var viewgroup = document.getElementById("viewgroup");
                        viewgroup.style = "display: none;";

                        var editgroup = document.getElementById("editgroup");
                        editgroup.style = "display: none;";

                                var getalumni = document.getElementById("getalumni");
                                getalumni.style = "display: none;";

                        var editlogin = document.getElementById("editlogin");
                        editlogin.style = "display: none;";

                             var addgroup = document.getElementById("addgroup");
                             addgroup.style = "display: none;";

                                var getjobs = document.getElementById("getjobs");
                                getjobs.style = "display: none;";

                        var userprofile = document.getElementById("userprofile");
                        userprofile.style = "display: block;";
            });

        $("#editprofilebtn").click(function(){
                    var userprofile = document.getElementById("userprofile");
                    userprofile.style = "display: none;";
                    var editlogin = document.getElementById("editlogin");
                    editlogin.style = "display: none;";
                    var viewgroup = document.getElementById("viewgroup");
                    viewgroup.style = "display: none;";
                    var editgroup = document.getElementById("editgroup");
                    editgroup.style = "display: none;";

                                var getalumni = document.getElementById("getalumni");
                                getalumni.style = "display: none;";

                             var addgroup = document.getElementById("addgroup");
                             addgroup.style = "display: none;";

                    data = userData;

                    var fname = document.getElementById("editfname");
                    fname.value = data.f_name;

                    var lname = document.getElementById("editlname");
                    lname.value = data.l_name;

                    var email = document.getElementById("editemail");
                    email.value = data.email;

                    var location = document.getElementById("editlocation");
                    location.value = data.location;

                    var contact = document.getElementById("editcontact");
                    contact.value = data.contact;

                    var editprofile = document.getElementById("editprofile");
                    editprofile.style = "display: block;";
        });

    $("#editprofilesubmit").click(function(){
                    $.ajax({
                    type: "POST",
                    async: false,
                    url: "http://localhost:8080/UpdateUserServlet",
                    data: {uid: userId,
                           fname: document.getElementById("editfname").value,
                           lname: document.getElementById("editlname").value,
                           email: document.getElementById("editemail").value,
                           location:document.getElementById("editlocation").value,
                           contact: document.getElementById("editcontact").value
                           },
                    dataType: "text",
                    success: function(result,status,xhr) {
                    //window.alert(result);
                    window.alert("Your Profile was updated successfully. Please login again.");
                    window.location.href="http://localhost:8080/Website/Login.html";
                    },
                    error: function(xhr,status,error) {
                        window.alert("Oops! There was a problem. Please try again!");
                    }
                    }
                    );
            });

    $("#editloginbtn").click(function(){
                        var userprofile = document.getElementById("userprofile");
                        userprofile.style = "display: none;";

                        var editprofile = document.getElementById("editprofile");
                        editprofile.style = "display: none;";

                        var viewgroup = document.getElementById("viewgroup");
                        viewgroup.style = "display: none;";

                        var editgroup = document.getElementById("editgroup");
                        editgroup.style = "display: none;";

                             var addgroup = document.getElementById("addgroup");
                             addgroup.style = "display: none;";

                                var getalumni = document.getElementById("getalumni");
                                getalumni.style = "display: none;";

                                var getjobs = document.getElementById("getjobs");
                                getjobs.style = "display: none;";

                        data = userData;

                        var username = document.getElementById("editlogin_user_name");
                        username.innerHTML = data.user_name;

                        var editlogin = document.getElementById("editlogin");
                        editlogin.style = "display: block;";
            });

    $("#editloginsubmit").click(function(){
                        $.ajax({
                        type: "POST",
                        async: false,
                        url: "http://localhost:8080/UpdateUserAuthenticationServlet",
                        data: {uid: userId,
                               username: userData.user_name,
                               password: document.getElementById("editpassword").value,
                               },
                        dataType: "text",
                        success: function(result,status,xhr) {
                        //window.alert(result);
                        window.alert("Your Password was updated successfully. Please login again.");
                        window.location.href="http://localhost:8080/Website/Login.html";
                        },
                        error: function(xhr,status,error) {
                            window.alert("Oops! There was a problem. Please try again!");
                        }
                        }
                        );
                });

     $("#viewgroupbtn").click(function(){

                        $.ajax({
                        type: "POST",
                        async: false,
                        url: "http://localhost:8080/GetAllUserCompanyServlet",
                        data: {uid: userId},
                        dataType: "text",
                        success: function(result,status,xhr) {

                        userCompany = JSON.parse(result);
                        //window.alert(userCompany);
                        },
                        error: function(xhr,status,error) {
                            window.alert("Oops! There was a problem. Please try again!");
                        }
                        }
                        );

                        $.ajax({
                        type: "POST",
                        async: false,
                        url: "http://localhost:8080/GetAllUserInstituteServlet",
                        data: {uid: userId},
                        dataType: "text",
                        success: function(result,status,xhr) {
                        //window.alert(result);
                        userInstitute = JSON.parse(result);
                        //window.alert(userInstitute);
                        },
                        error: function(xhr,status,error) {
                            window.alert("Oops! There was a problem. Please try again!");
                        }
                        }
                        );


                        $.ajax({
                        type: "POST",
                        async: false,
                        url: "http://localhost:8080/GetAllUserExpertiseServlet",
                        data: {uid: userId},
                        dataType: "text",
                        success: function(result,status,xhr) {
                        //window.alert(result);
                        userExpertise = JSON.parse(result);
                        //window.alert(userInstitute);
                        },
                        error: function(xhr,status,error) {
                            window.alert("Oops! There was a problem. Please try again!");
                        }
                        }
                        );
                        //console.log(userCompany);
                        //console.log(userInstitute);

                        var userprofile = document.getElementById("userprofile");
                        userprofile.style = "display: none;";

                        var editprofile = document.getElementById("editprofile");
                        editprofile.style = "display: none;";

                        var editlogin = document.getElementById("editlogin");
                        editlogin.style = "display: none;";

                        var editgroup = document.getElementById("editgroup");
                        editgroup.style = "display: none;";

                             var addgroup = document.getElementById("addgroup");
                             addgroup.style = "display: none;";

                                var getalumni = document.getElementById("getalumni");
                                getalumni.style = "display: none;";

                                var getjobs = document.getElementById("getjobs");
                                getjobs.style = "display: none;";

                        var companylist = document.getElementById("companylist");
                        var institutelist = document.getElementById("institutelist");
                        var expertiselist = document.getElementById("expertiselist");

                        var str = "";
                        str += "<ol>"
                        for(i=0;i<userCompany.length;i++){
                            str += "<li>";
                            str += "Company: " + userCompany[i].cname + "<br>";
                            str += "Position: " + userCompany[i].position + "<br>";
                            str += "Start Date: " + userCompany[i].s_date + "<br>";
                            str += "End Date: " + userCompany[i].e_date + "<br>";
                            str += "</li>";
                        }
                        str += "</ol>";

                        companylist.innerHTML = str;

                        str = "";
                        str += "<ol>"
                        for(i=0;i<userInstitute.length;i++){
                            str += "<li>";
                            str += "Institute: " + userInstitute[i].iname + "<br>";
                            str += "Degree: " + userInstitute[i].degree + "<br>";
                            str += "Start Date: " + userInstitute[i].s_date + "<br>";
                            str += "End Date: " + userInstitute[i].e_date + "<br>";
                            str += "</li>";
                        }
                        str += "</ol>";

                        institutelist.innerHTML = str;

                        str = "";
                        str += "<ol>"
                        for(i=0;i<userExpertise.length;i++){
                            str += "<li>";
                            str += "Expertise: " + userExpertise[i].ename + "<br>";
                            str += "Level: " + userExpertise[i].level + "<br>";
                            str += "</li>";
                        }
                        str += "</ol>";
                        expertiselist.innerHTML = str;


                        var viewgroup = document.getElementById("viewgroup");
                        viewgroup.style = "display: block;";
                 });

     $("#editgroupbtn").click(function(){


                             $.ajax({
                             type: "POST",
                             async: false,
                             url: "http://localhost:8080/GetAllUserCompanyServlet",
                             data: {uid: userId},
                             dataType: "text",
                             success: function(result,status,xhr) {
                             //window.alert(result);
                             userCompany = JSON.parse(result);
                             },
                             error: function(xhr,status,error) {
                                 window.alert("Oops! There was a problem. Please try again!");
                             }
                             }
                             );

                             $.ajax({
                             type: "POST",
                             async: false,
                             url: "http://localhost:8080/GetAllUserInstituteServlet",
                             data: {uid: userId},
                             dataType: "text",
                             success: function(result,status,xhr) {
                             //window.alert(result);
                             userInstitute = JSON.parse(result);
                             },
                             error: function(xhr,status,error) {
                                 window.alert("Oops! There was a problem. Please try again!");
                             }
                             }
                             );

                            $.ajax({
                            type: "POST",
                            async: false,
                            url: "http://localhost:8080/GetAllUserExpertiseServlet",
                            data: {uid: userId},
                            dataType: "text",
                            success: function(result,status,xhr) {
                            //window.alert(result);
                            userExpertise = JSON.parse(result);
                            //window.alert(userInstitute);
                            },
                            error: function(xhr,status,error) {
                                window.alert("Oops! There was a problem. Please try again!");
                            }
                            }
                            );
                             //console.log(userCompany);
                             //console.log(userInstitute);

                             var userprofile = document.getElementById("userprofile");
                             userprofile.style = "display: none;";

                             var editprofile = document.getElementById("editprofile");
                             editprofile.style = "display: none;";

                             var editlogin = document.getElementById("editlogin");
                             editlogin.style = "display: none;";

                             var viewgroup = document.getElementById("viewgroup");
                             viewgroup.style = "display: none;";

                             var addgroup = document.getElementById("addgroup");
                             addgroup.style = "display: none;";

                                var getjobs = document.getElementById("getjobs");
                                getjobs.style = "display: none;";

                                var getalumni = document.getElementById("getalumni");
                                getalumni.style = "display: none;";

                             var companylist = document.getElementById("editcompanylist");
                             var institutelist = document.getElementById("editinstitutelist");
                             var expertiselist = document.getElementById("editexpertiselist");

                             var str = "";
                             str += "<ol>"
                             for(i=0;i<userCompany.length;i++){
                                 str += '<li id="c' + i + '"' + ' class="editcompany">';
                                 str += "Company: " + userCompany[i].cname + "<br>";
                                 str += "Position: " + userCompany[i].position + "<br>";
                                 str += "Start Date: " + userCompany[i].s_date + "<br>";
                                 str += "End Date: " + userCompany[i].e_date + "<br>";
                                 str += '<button type="button" class="btn btn-primary btn3 deletecbtn" id="cd'+i+'">Delete</button>' + " " + '<button type="button" class="btn btn-primary btn3 editcbtn" id="ce' + i +'">Edit</button>';
                                 str += "</li>";

                             }
                             str += "</ol>";

                             companylist.innerHTML = str;

                             str = "";
                             str += "<ol>"
                             for(i=0;i<userInstitute.length;i++){
                                 str += '<li id="i' + i + '"' + ' class="editinstitute">';
                                 str += "Institute: " + userInstitute[i].iname + "<br>";
                                 str += "Degree: " + userInstitute[i].degree + "<br>";
                                 str += "Start Date: " + userInstitute[i].s_date + "<br>";
                                 str += "End Date: " + userInstitute[i].e_date + "<br>";
                                 str += '<button type="button" class="btn btn-primary btn3 deleteibtn" id="id'+i+'">Delete</button>' + " " + '<button type="button" class="btn btn-primary btn3 editibtn" id="ie' + i +'">Edit</button>';
                                 str += "</li>";
                             }
                             str += "</ol>";

                             institutelist.innerHTML = str;

                             str = "";
                             str += "<ol>"
                             for(i=0;i<userExpertise.length;i++){
                                 str += '<li id="e' + i + '"' + ' class="editexpertise">';
                                 str += "Expertise: " + userExpertise[i].ename + "<br>";
                                 str += "Level: " + userExpertise[i].level + "<br>";
                                 str += '<button type="button" class="btn btn-primary btn3 deleteebtn" id="ed'+i+'">Delete</button>' + " " + '<button type="button" class="btn btn-primary btn3 editebtn" id="ee' + i +'">Edit</button>';
                                 str += "</li>";
                             }
                             str += "</ol>";
                             expertiselist.innerHTML = str;

                             var editgroup = document.getElementById("editgroup");
                             editgroup.style = "display: block;";
                      });


     $("body").on('click','.editcbtn',function(event){
                             var id = event.target.id.charAt(2);

                             var companylistelement = document.getElementById("c" + id);
                             var str = "";

                             str += "Company: " + userCompany[id].cname + "<br>";
                             str += "Position: " + '<input type="text" id="' + id +'cpos"' +' value="' +userCompany[id].position  + '">' + "<br>";
                             str += "Start Date: " + '<input type="text" id="' + id +'csdate"' +' value="' +userCompany[id].s_date  + '">'+ "<br>";
                             str += "End Date: " + '<input type="text" id="' + id +'cedate"' +' value="' +userCompany[id].e_date  + '">'+ "<br>";
                             str += '<button type="button" class="btn btn-primary btn3 savecbtn" id="cs' + id +'">Save</button>';

                             companylistelement.innerHTML = str;
                     });

     $("body").on('click','.editibtn',function(event){
                             var id = event.target.id.charAt(2);

                             var institutelistelement = document.getElementById("i" + id);
                             var str = "";

                             str += "Institute: " + userInstitute[id].iname + "<br>";
                             str += "Degree: " + '<input type="text" id="' + id +'ideg"' +' value="' +userInstitute[id].degree  + '">' + "<br>";
                             str += "Start Date: " + '<input type="text" id="' + id +'isdate"' +' value="' +userInstitute[id].s_date  + '">'+ "<br>";
                             str += "End Date: " + '<input type="text" id="' + id +'iedate"' +' value="' +userInstitute[id].e_date  + '">'+ "<br>";
                             str += '<button type="button" class="btn btn-primary btn3 saveibtn" id="is' + id +'">Save</button>';

                             institutelistelement.innerHTML = str;
                     });

    $("body").on('click','.editebtn',function(event){
                             var id = event.target.id.charAt(2);

                             var expertiselistelement = document.getElementById("e" + id);
                             var str = "";

                             str += "Expertise: " + userExpertise[id].ename + "<br>";
                             str += "Level: " + '<input type="text" id="' + id +'elvl"' +' value="' +userExpertise[id].level  + '">' + "<br>";
                             str += '<button type="button" class="btn btn-primary btn3 saveebtn" id="es' + id +'">Save</button>';

                             expertiselistelement.innerHTML = str;
                     });

     $("body").on('click','.savecbtn',function(event){
                                  var id = event.target.id.charAt(2);

                                  var companylistelement = document.getElementById("c" + id);
                                  var str = "";
                                  //window.alert(id);
                                  //window.alert(userCompany);

                                   $.ajax({
                                   type: "POST",
                                   async: false,
                                   url: "http://localhost:8080/UpdateUserCompanyServlet",
                                   data: {uid: userId,
                                          cid: userCompany[id].c_id,
                                          position: document.getElementById(id+"cpos").value,
                                          sdate: document.getElementById(id+"csdate").value,
                                          edate: document.getElementById(id+"cedate").value
                                          },
                                   dataType: "text",
                                   success: function(result,status,xhr) {
                                   //window.alert(result);
                                   window.alert("User Company Successfully Updated");
                                   },
                                   error: function(xhr,status,error) {
                                       window.alert("Oops! There was a problem. Please try again!");
                                   }
                                   }
                                   );

                                  str += "Company: " + userCompany[id].cname + "<br>";
                                  str += "Position: " + document.getElementById(id+"cpos").value+ "<br>";
                                  str += "Start Date: " + document.getElementById(id+"csdate").value + "<br>";
                                  str += "End Date: " + document.getElementById(id+"cedate").value + "<br>";
                                  str += '<button type="button" class="btn btn-primary btn3 deletecbtn" id="cd'+id+'">Delete</button>' + " " + '<button type="button" class="btn btn-primary btn3 editcbtn" id="ce' + id +'">Edit</button>';

                                  userCompany[id].position = document.getElementById(id+"cpos").value;
                                  userCompany[id].s_date = document.getElementById(id+"csdate").value;
                                  userCompany[id].e_date = document.getElementById(id+"cedate").value;
                                  companylistelement.innerHTML = str;
                          });

        $("body").on('click','.saveibtn',function(event){
                                  var id = event.target.id.charAt(2);

                                  var institutelistelement = document.getElementById("i" + id);
                                  var str = "";

                                   $.ajax({
                                   type: "POST",
                                   async: false,
                                   url: "http://localhost:8080/UpdateUserInstituteServlet",
                                   data: {uid: userId,
                                          iid: userInstitute[id].i_id,
                                          degree: document.getElementById(id+"ideg").value,
                                          sdate: document.getElementById(id+"isdate").value,
                                          edate: document.getElementById(id+"iedate").value
                                          },
                                   dataType: "text",
                                   success: function(result,status,xhr) {
                                   //window.alert(result);
                                   window.alert("User Institute Successfully Updated");
                                   },
                                   error: function(xhr,status,error) {
                                       window.alert("Oops! There was a problem. Please try again!");
                                   }
                                   }
                                   );

                                  str += "Institute: " + userInstitute[id].iname + "<br>";
                                  str += "Degree: " + document.getElementById(id+"ideg").value+ "<br>";
                                  str += "Start Date: " + document.getElementById(id+"isdate").value + "<br>";
                                  str += "End Date: " + document.getElementById(id+"iedate").value + "<br>";
                                  str += '<button type="button" class="btn btn-primary btn3 deleteibtn" id="id'+id+'">Delete</button>' + " " + '<button type="button" class="btn btn-primary btn3 editibtn" id="ie' + id +'">Edit</button>';

                                  userInstitute[id].degree = document.getElementById(id+"ideg").value;
                                  userInstitute[id].s_date = document.getElementById(id+"isdate").value;
                                  userInstitute[id].e_date = document.getElementById(id+"iedate").value;
                                  institutelistelement.innerHTML = str;
                          });

        $("body").on('click','.saveebtn',function(event){
                                  var id = event.target.id.charAt(2);

                                  var expertiselistelement = document.getElementById("e" + id);
                                  var str = "";

                                   $.ajax({
                                   type: "POST",
                                   async: false,
                                   url: "http://localhost:8080/UpdateUserExpertiseServlet",
                                   data: {uid: userId,
                                          eid: userExpertise[id].e_id,
                                          level: document.getElementById(id+"elvl").value,
                                          },
                                   dataType: "text",
                                   success: function(result,status,xhr) {
                                   //window.alert(result);
                                   window.alert("User Expertise Successfully Updated");
                                   },
                                   error: function(xhr,status,error) {
                                       window.alert("Oops! There was a problem. Please try again!");
                                   }
                                   }
                                   );

                                  str += "Expertise: " + userExpertise[id].ename + "<br>";
                                  str += "Level: " + document.getElementById(id+"elvl").value+ "<br>";
                                  str += '<button type="button" class="btn btn-primary btn3 deleteebtn" id="ed'+id+'">Delete</button>' + " " + '<button type="button" class="btn btn-primary btn3 editebtn" id="ee' + id +'">Edit</button>';

                                  userExpertise[id].level = document.getElementById(id+"elvl").value;
                                  expertiselistelement.innerHTML = str;
                          });

        $("body").on('click','.deleteibtn',function(event){
                             var id = event.target.id.charAt(2);

                             var institutelistelement = document.getElementById("i" + id);
                             var str = "";

                             $.ajax({
                                   type: "POST",
                                   async: false,
                                   url: "http://localhost:8080/DeleteUserInstituteServlet",
                                   data: {uid: userId,
                                          iid: userInstitute[id].i_id
                                          },
                                   dataType: "text",
                                   success: function(result,status,xhr) {
                                   //window.alert(result);
                                   window.alert("User Institute Successfully Deleted");
                                   },
                                   error: function(xhr,status,error) {
                                       window.alert("Oops! There was a problem. Please try again!");
                                   }
                                   }
                                   );

                             institutelistelement.innerHTML = "Entry Deleted Successfully";
                     });

        $("body").on('click','.deletecbtn',function(event){
                                     var id = event.target.id.charAt(2);

                                     var companylistelement = document.getElementById("c" + id);
                                     var str = "";

                                     $.ajax({
                                           type: "POST",
                                           async: false,
                                           url: "http://localhost:8080/DeleteUserCompanyServlet",
                                           data: {uid: userId,
                                                  cid: userCompany[id].c_id
                                                  },
                                           dataType: "text",
                                           success: function(result,status,xhr) {
                                           //window.alert(result);
                                           window.alert("User Company Successfully Deleted");
                                           },
                                           error: function(xhr,status,error) {
                                               window.alert("Oops! There was a problem. Please try again!");
                                           }
                                           }
                                           );

                                     companylistelement.innerHTML = "Entry Deleted Successfully";
                             });

        $("body").on('click','.deleteebtn',function(event){
                                     var id = event.target.id.charAt(2);

                                     var expertiselistelement = document.getElementById("e" + id);
                                     var str = "";

                                     $.ajax({
                                           type: "POST",
                                           async: false,
                                           url: "http://localhost:8080/DeleteUserExpertiseServlet",
                                           data: {uid: userId,
                                                  eid: userExpertise[id].e_id
                                                  },
                                           dataType: "text",
                                           success: function(result,status,xhr) {
                                           //window.alert(result);
                                           window.alert("User Expertise Successfully Deleted");
                                           },
                                           error: function(xhr,status,error) {
                                               window.alert("Oops! There was a problem. Please try again!");
                                           }
                                           }
                                           );

                                     expertiselistelement.innerHTML = "Entry Deleted Successfully";
                             });

        var ctypingTimer,itypingTimer,etypingTimer,jctypingTimer,jttypingTimer;
        var doneTypingInterval = 1000;
        $("body").on("keyup","#addcompany",function(event){
                clearTimeout(ctypingTimer);
                    if ($("#addcompany").val()  && document.getElementById("addcompany").value.length>1) {
                        ctypingTimer = setTimeout(doneTypingc, doneTypingInterval);
                    }
        });

        $("body").on("keyup","#addinstitute",function(event){
                clearTimeout(itypingTimer);
                    if ($("#addinstitute").val()  && document.getElementById("addinstitute").value.length>1) {
                        itypingTimer = setTimeout(doneTypingi, doneTypingInterval);
                    }
        });

        $("body").on("keyup","#addexpertise",function(event){
                        clearTimeout(etypingTimer);
                            if ($("#addexpertise").val() && document.getElementById("addexpertise").value.length>1) {
                                etypingTimer = setTimeout(doneTypinge, doneTypingInterval);
                            }
                });

        $("body").on("keyup","#addjobcompany",function(event){
                clearTimeout(jctypingTimer);
                    if ($("#addjobcompany").val()  && document.getElementById("addjobcompany").value.length>1) {
                        jctypingTimer = setTimeout(doneTypingjc, doneTypingInterval);
                    }
        });

        $("#addgroupbtn").click(function(){
                                var editprofile = document.getElementById("editprofile");
                                editprofile.style = "display: none;";

                                var viewgroup = document.getElementById("viewgroup");
                                viewgroup.style = "display: none;";

                                var editgroup = document.getElementById("editgroup");
                                editgroup.style = "display: none;";

                                var editlogin = document.getElementById("editlogin");
                                editlogin.style = "display: none;";

                                var userprofile = document.getElementById("userprofile");
                                userprofile.style = "display: none;";

                                var getjobs = document.getElementById("getjobs");
                                getjobs.style = "display: none;";

                                var getalumni = document.getElementById("getalumni");
                                getalumni.style = "display: none;";

                                var addgroup = document.getElementById("addgroup");
                                addgroup.style = "display: block;";

                    });

            $("body").on('click','#addcompanysubmit',function(event){

                                     var c_id = document.getElementById("addcompany").value.charAt(0);
                                     var pos = document.getElementById("addposition").value;
                                     var s_date = document.getElementById("caddsdate").value;
                                     var e_date = document.getElementById("caddedate").value;


                                     $.ajax({
                                           type: "POST",
                                           async: false,
                                           url: "http://localhost:8080/AddUserCompanyServlet",
                                           data: {uid: userId,
                                                  cid: companies[c_id].c_id,
                                                  position: pos,
                                                  sdate: s_date,
                                                  edate: e_date
                                                  },
                                           dataType: "text",
                                           success: function(result,status,xhr) {
                                           //window.alert(result);
                                           window.alert("User Company Successfully Added");
                                           },
                                           error: function(xhr,status,error) {
                                               window.alert("Oops! There was a problem. Please try again!");
                                           }
                                           }
                                           );

                                     document.getElementById("addcompany").value = "";
                                     document.getElementById("addposition").value = "";
                                     document.getElementById("caddsdate").value = "";
                                     document.getElementById("caddedate").value = "";
                             });

            $("body").on('click','#addinstitutesubmit',function(event){

                                     var i_id = document.getElementById("addinstitute").value.charAt(0);
                                     var deg = document.getElementById("adddegree").value;
                                     var s_date = document.getElementById("iaddsdate").value;
                                     var e_date = document.getElementById("iaddedate").value;


                                     $.ajax({
                                           type: "POST",
                                           async: false,
                                           url: "http://localhost:8080/AddUserInstituteServlet",
                                           data: {uid: userId,
                                                  iid: institutes[i_id].i_id,
                                                  degree: deg,
                                                  sdate: s_date,
                                                  edate: e_date
                                                  },
                                           dataType: "text",
                                           success: function(result,status,xhr) {
                                           //window.alert(result);
                                           window.alert("User Institute Successfully Added");
                                           },
                                           error: function(xhr,status,error) {
                                               window.alert("Oops! There was a problem. Please try again!");
                                           }
                                           }
                                           );

                                     document.getElementById("addinstitute").value = "";
                                     document.getElementById("adddegree").value = "";
                                     document.getElementById("iaddsdate").value = "";
                                     document.getElementById("iaddedate").value = "";
                             });

            $("body").on('click','#addexpertisesubmit',function(event){

                                     var e_id = document.getElementById("addexpertise").value.charAt(0);
                                     var lvl = document.getElementById("addlevel").value;

                                     $.ajax({
                                           type: "POST",
                                           async: false,
                                           url: "http://localhost:8080/AddUserExpertiseServlet",
                                           data: {uid: userId,
                                                  eid: expertises[e_id].e_id,
                                                  level: lvl,
                                                  },
                                           dataType: "text",
                                           success: function(result,status,xhr) {
                                           //window.alert(result);
                                           window.alert("User Expertise Successfully Added");
                                           },
                                           error: function(xhr,status,error) {
                                               window.alert("Oops! There was a problem. Please try again!");
                                           }
                                           }
                                           );

                                     document.getElementById("addexpertise").value = "";
                                     document.getElementById("addlevel").value = "";
                             });

            $("body").on('click','#getjobsbtn',function(event){
                                var editprofile = document.getElementById("editprofile");
                                editprofile.style = "display: none;";

                                var viewgroup = document.getElementById("viewgroup");
                                viewgroup.style = "display: none;";

                                var editgroup = document.getElementById("editgroup");
                                editgroup.style = "display: none;";

                                var editlogin = document.getElementById("editlogin");
                                editlogin.style = "display: none;";

                                var userprofile = document.getElementById("userprofile");
                                userprofile.style = "display: none;";

                                var addgroup = document.getElementById("addgroup");
                                addgroup.style = "display: none;";

                                var getalumni = document.getElementById("getalumni");
                                getalumni.style = "display: none;";

                                var getjobs = document.getElementById("getjobs");
                                getjobs.style = "display: block;";

                                var jobform = document.getElementById("jobform");
                                jobform.style = "display: block;";
             });

             $("body").on('click','#getjobssubmit',function(event){

                                     var c_id = document.getElementById("addjobcompany").value.charAt(0);
                                     var exp = document.getElementById("jobexp").value;

                                     $.ajax({
                                           type: "POST",
                                           async: false,
                                           url: "http://localhost:8080/GetJobServlet",
                                           data: {cid: companies[c_id].c_id,
                                                  exp: exp,
                                                  },
                                           dataType: "text",
                                           success: function(result,status,xhr) {
                                           //window.alert(result);
                                            jobs = JSON.parse(result);
                                           },
                                           error: function(xhr,status,error) {
                                               window.alert("Oops! There was a problem. Please try again!");
                                           }
                                           }
                                           );

                                 var jobform = document.getElementById("jobform");
                                 jobform.style = "display: none;";


                                var jobcompanyoption = document.getElementById("jobcompanyoption");
                                var str = "";
                                str += "<ol>"
                                for(i=0;i<jobs.length;i++){
                                    str += "<li>";
                                    str += "Company: " + jobs[i].company + "<br>";
                                    str += "Category: " + jobs[i].category + "<br>";
                                    str += "Min-Exp: " + jobs[i].min_exp + "<br>";
                                    str += "Min-Exp: " + jobs[i].max_exp + "<br>";
                                    str += "Location: " + jobs[i].location + "<br>";
                                    str += '<button type="button" class="btn btn-primary btn3 applybtn" id="apply'+i+'">Apply</button>';
                                    str += "</li>";
                                }
                                str += "</ol>";
                                jobcompanyoption.innerHTML = str;

                             });

              $("body").on('click','#getalumnibtn',function(event){
                                        var alumni;
                                        var editprofile = document.getElementById("editprofile");
                                        editprofile.style = "display: none;";

                                        var viewgroup = document.getElementById("viewgroup");
                                        viewgroup.style = "display: none;";

                                        var editgroup = document.getElementById("editgroup");
                                        editgroup.style = "display: none;";

                                        var editlogin = document.getElementById("editlogin");
                                        editlogin.style = "display: none;";

                                        var userprofile = document.getElementById("userprofile");
                                        userprofile.style = "display: none;";

                                        var addgroup = document.getElementById("addgroup");
                                        addgroup.style = "display: none;";

                                        var getjobs = document.getElementById("getjobs");
                                        getjobs.style = "display: none;";

                                        var jobform = document.getElementById("jobform");
                                        jobform.style = "display: none;";



                                                   $.ajax({
                                                         type: "POST",
                                                         async: false,
                                                         url: "http://localhost:8080/GetUserAlumniServlet",
                                                         data: {userid: userId,
                                                                },
                                                         dataType: "text",
                                                         success: function(result,status,xhr) {
                                                         //window.alert(result);
                                                          alumni = JSON.parse(result);
                                                         },
                                                         error: function(xhr,status,error) {
                                                             window.alert("Oops! There was a problem. Please try again!");
                                                         }
                                                         }
                                                         );

                                        var alumnilist = document.getElementById("getalumnilist");

                                        var str = "";
                                        str + = "<ol>"
                                        for(i=0;i<alumni.length;i++)
                                            {
                                             str += "<li>";
                                             str += "Name: " + alumni[i].f_name + " " + alumni[i].l_name + "<br>";
                                             str += "Email: " + alumni[i].email + "<br>";
                                             str += "Location: " + alumni[i].location + "<br>";
                                             str += "Contact: " + alumni[i].contact + "<br>";
                                             str += "</li>";
                                            }
                                        str += "</ol>";
                                        alumnilist.innerHTML = str;

                                        var getalumni = document.getElementById("getalumni");
                                        getalumni.style = "display: block;";

                                           });

              $("body").on('click','.applybtn',function(event){
                                     var id = event.target.id.charAt(5);

                                     var joblistelement = document.getElementById("apply" + id);
                                     var str = "";

                                     $.ajax({
                                           type: "POST",
                                           async: false,
                                           url: "http://localhost:8080/AddUserJobApplicationServlet",
                                           data: {uid: userId,
                                                  jid: jobs[id].j_id
                                                  },
                                           dataType: "text",
                                           success: function(result,status,xhr) {
                                           //window.alert(result);
                                           window.alert("Successfully Applied For Job");
                                           },
                                           error: function(xhr,status,error) {
                                               window.alert("Oops! There was a problem. Please try again!");
                                           }
                                           }
                                           );

                                    str += "Company: " + jobs[id].company + "<br>";
                                    str += "Category: " + jobs[id].category + "<br>";
                                    str += "Min-Exp: " + jobs[id].exp + "<br>";
                                    str += "Location: " + jobs[id].location + "<br>";
                                    str += "Applied";
                                    str += "</li>";
                                    joblistelement.innerHTML = str;
                             });

});

function doneTypingc(){
    $.ajax({
     type: "POST",
     async: false,
     url: "http://localhost:8080/GetCompaniesByKeywordServlet",
     data: {keyword: document.getElementById("addcompany").value,
            },
     dataType: "text",
     success: function(result,status,xhr) {
     //window.alert(result);
     companies = JSON.parse(result);
     //window.alert(companies);
     var companyoption = document.getElementById("companyoption");
     var str ="";
     for(i=0;i<companies.length;i++){
        str += '<option value="' + i +" " +companies[i].c_name + '" ></option>';
     }
     str += '<option value="None"></option>';
     //window.alert(str);
     companyoption.innerHTML = str;
     },
     error: function(xhr,status,error) {
     window.alert("Oops! There was a problem. Please try again!");
     }
     }
     );
}

function doneTypingjc(){
    $.ajax({
     type: "POST",
     async: false,
     url: "http://localhost:8080/GetCompaniesByKeywordServlet",
     data: {keyword: document.getElementById("addjobcompany").value,
            },
     dataType: "text",
     success: function(result,status,xhr) {
     //window.alert(result);
     companies = JSON.parse(result);
     //window.alert(companies);
     var companyoption = document.getElementById("jobcompanyoption");
     var str ="";
     for(i=0;i<companies.length;i++){
        str += '<option value="' + i +" " +companies[i].c_name + '" ></option>';
     }
     str += '<option value="None"></option>';
     //window.alert(str);
     companyoption.innerHTML = str;
     },
     error: function(xhr,status,error) {
     window.alert("Oops! There was a problem. Please try again!");
     }
     }
     );
}

function doneTypingi(){
    $.ajax({
     type: "POST",
     async: false,
     url: "http://localhost:8080/GetInstitutesByKeywordServlet",
     data: {keyword: document.getElementById("addinstitute").value,
            },
     dataType: "text",
     success: function(result,status,xhr) {
     //window.alert(result);
     institutes = JSON.parse(result);
     //window.alert(companies);
     var instituteoption = document.getElementById("instituteoption");
     var str ="";
     for(i=0;i<institutes.length;i++){
        str += '<option value="' + i +" " +institutes[i].i_name + '" ></option>';
     }
     str += '<option value="None"></option>';
     // window.alert(str);
     instituteoption.innerHTML = str;
     },
     error: function(xhr,status,error) {
     window.alert("Oops! There was a problem. Please try again!");
     }
     }
     );
}

function doneTypinge(){
    $.ajax({
     type: "POST",
     async: false,
     url: "http://localhost:8080/GetExpertisesByKeywordServlet",
     data: {keyword: document.getElementById("addexpertise").value,
            },
     dataType: "text",
     success: function(result,status,xhr) {
     //window.alert(result);
     expertises = JSON.parse(result);
     //window.alert(companies);
     var expertiseoption = document.getElementById("expertiseoption");
     var str ="";
     for(i=0;i<expertises.length;i++){
        str += '<option value="' + i +" " + expertises[i].e_name + '" ></option>';
     }
     str += '<option value="None"></option>';
      //window.alert(str);
     expertiseoption.innerHTML = str;
     },
     error: function(xhr,status,error) {
     window.alert("Oops! There was a problem. Please try again!");
     }
     }
     );
}