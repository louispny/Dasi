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

$(document).ready( function () {
    $('#boutonFin').on( 'click', function () { // Fonction appelée lors du clic sur le bouton
        $('#popupFin').css('display', 'block');
    });
});

$(document).ready( function () {
    $('#boutonContinuer').on( 'click', function () { // Fonction appelée lors du clic sur le bouton
        
        console.log("clic sur le bouton de Continuer"); 
        $('#messageAttente').html("Demande en cours de traitement...");
        // Appel AJAX
        $.ajax({
            url: './ActionServlet',
            method: 'POST',
            data: {
                todo: 'envoiNote',
                note: note
            },
            dataType: 'json'
        })
        .done( function () { // Fonction appelée en cas d'appel AJAX réussi
            window.location.href = "accueilEleve.html";
        })
        .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
            console.log('Error',error); // LOG dans Console Javascript
            alert("Erreur lors de l'appel AJAX");
        })
        .always( function () { // Fonction toujours appelée
            
        });
    });
});