$('document').ready(function(){

    $('#editUserProfileBtn').on('click', function(event){

        event.preventDefault();
        var href = $(this).attr("href");

        $.get(href, function(user, status){
            $('#idUserProfile').val(user.id);
            $('#newFirstNameProfile').val(user.firstName);
            $('#newLastNameProfile').val(user.lastName);
            $('#newEmailProfile').val(user.email);
            $('#newPhoneProfile').val(user.phone);
        });

        $('#editProfileModal').modal();
    });

    $('#changePasswordProfileSubmit').on('click', function(event){
        var newPassword = $("#newPassword").val();
        var confirmNewPassword =$("#newPasswordConfirmation").val();

        if(newPassword != confirmNewPassword){
            alert("Passwords don't match");
            return false;
        }
    })

     $('#deleteAccountSubmit').on('click', function(event){
            var password = $("#deleteAccountPassword").val();
            var confirmPassword =$("#confirmDeleteAccountPassword").val();

            if(password != confirmPassword){
                alert("Passwords don't match");
                return false;
            }
     })

});