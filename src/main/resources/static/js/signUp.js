 $(document).ready(function() {
                $('#createAccountSubmit').click(function(){
                    var user = $("#usernameInput").val();

                    $.ajax({
                    type:"POST",
                    url: "/checkTheUsername",
                    data: { username:  $("#usernameInput").val() },
//                    dataType: 'json',
                    success : function(data){
                       if(data == "AVAILABLE"){
                               alert("LETS SEE" + data);
                       }else{
                            alert("NOT" + data);
                       }
                    },
                    always: function() {
                       alert("ALWAYS");
//                       $("#formtopost").submit();
                    }
                });
              });
            });