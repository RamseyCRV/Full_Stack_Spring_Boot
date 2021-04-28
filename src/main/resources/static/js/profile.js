$('document').ready(function(){

//    $('table #deleteNoteBtn').on('click', function(event){
//        event.preventDefault();
//        var href = $(this).attr("href");
//        $("#confirmDeleteNoteBtn").attr('href', href);
//        $('#deleteNoteModal').modal();
//    });

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

});