$('document').ready(function(){

    $('table #deleteNoteBtn').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr("href");
        $("#confirmDeleteNoteBtn").attr('href', href);
        $('#deleteNoteModal').modal();
    });

    $('table #showNoteBtn').on('click', function(event){

        event.preventDefault();
        var href = $(this).attr("href");

        $.get(href, function(note, status){
            $('#noteEditId').val(note.notesId);
            $('#noteEditTitle').val(note.title);
            $('#noteEditText').val(note.text);
        });

        $('#showNoteModal').modal();
    });

});