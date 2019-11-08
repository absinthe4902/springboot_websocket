$(document).ready(function () {



    $('#enter').on('click',  function () {
        let name = $('#name').val();

        console.log("순서");


        if(name.length ===0) {
          $('#name').addClass('shake');
            console.log("순서2");

        }else {
            location.href = '/list?name=' + name;
        }


    });



});