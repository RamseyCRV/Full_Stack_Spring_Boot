$('document').ready(function(){

    $('table #deleteTodoBtn').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr("href");
        $("#confirmDeleteTodoBtn").attr('href', href);
        $('#deleteTodoModal').modal();
    });

    $('table #editTodoBtn').on('click', function(event){

        event.preventDefault();
        var href = $(this).attr("href");

        $.get(href, function(todo, status){
            $('#idEditModal').val(todo.todoId);
            $('#newTodoEditModal').val(todo.text);
        });

        $('#editTodoModal').modal();
    });


    $('#workingTodoBtn').on('click', function(event){
           $('#activeTodos').toggle();

    });

    $('#finishedTodoBtn').on('click', function(event){
           $('#inactiveTodos').toggle();
    });

});