let note=0;

$('#stars').on('click', '.clic', function(event) {
    $('.star').css('fill','gray');
    console.log(event.target.id);
    note = parseInt(event.target.id.substring(4));
    console.log(note);
    for (let i = 1; i <= note; i++) {
        $('#starn'+i).css('fill','#FFD700');
    }
});