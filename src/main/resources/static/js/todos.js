$('document').ready(function(){

    $('table #deleteTodoBtn').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr("href");
        $("#confirmDeleteTodoBtn").attr('href', href);
        $('#deleteTodoModal').modal();
    });

});