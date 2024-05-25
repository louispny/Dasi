let note=0;



$(document).ready( function () {
    $('#boutonFin').on( 'click', function () { // Fonction appelée lors du clic sur le bouton
        $('#popupFin').css('display', 'block');
    });
});

$(document).ready( function () {
    $('#boutonContinuer').on( 'click', function () { // Fonction appelée lors du clic sur le bouton
        
        console.log("clic sur le bouton de Continuer"); 
        $('#messageAttente').html("Demande en cours de traitement...");
        
        let champBilan = $('#bilanSoutien').val();
        // Appel AJAX
        $.ajax({
            url: './ActionServlet',
            method: 'POST',
            data: {
                todo: 'envoiBilan',
                bilan : champBilan
            },
            dataType: 'json'
        })
        .done( function (response) { // Fonction appelée en cas d'appel AJAX réussi
            if (response.validation ) {
                console.log("Bilan envoyé");
                window.location.href = "accueilIntervenant.html";
            }
            
        })
        .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
            console.log('Error',error); // LOG dans Console Javascript
            alert("Erreur lors de l'appel AJAX");
        })
        .always( function () { // Fonction toujours appelée
            
        });
    });
});/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


