let note=0;

$('#stars').on('click', '.star', function(event) {
    //passage des étoiles précédentes en jaune
    note = parseInt(event.target.id.substring(4));
    console.log(note);
    for (let i = 1; i <= note; i++) {
        $('#star'+i).css('fill','#FFD700');
    }
});