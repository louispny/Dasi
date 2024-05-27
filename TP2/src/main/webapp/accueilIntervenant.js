let matiere = "";

function afficherInformationsIntervenant() {
    // on récupére les informations de l'élève avec une requête AJAX
    // on récupére les informations de l'élève
    console.log("récupération des infos de l’intervenant"); // LOG dans Console Javascript

    // Appel AJAX
    $.ajax({
        url: './ActionServlet',
        method: 'POST',
        data: {
            todo: 'getIntervenant'
        },
        dataType: 'json'
    })
    .done( function (response) { // Fonction appelée en cas d'appel AJAX réussi
        console.log('Response',response); // LOG dans Console Javascript
        if (response.getIntervenant) {
            // // TODO: afficher les informations de l'élève
            $('#nomIntervenant').html(response.utilisateur.prenom + " " + response.utilisateur.nom); // Message pour le paragraphe de notification
        }
        else {
            $('#nomIntervenant').html("Jsp"); // Message pour le paragraphe de notification
        }
    })
    .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
        console.log('Error',error); // LOG dans Console Javascript
        alert("Erreur lors de l'appel AJAX");
    });
}

function afficherInformationsIntervention() {
    // on récupére les informations de l'élève avec une requête AJAX
    // on récupére les informations de l'élève
    console.log("récupération des infos de l’intervention"); // LOG dans Console Javascript

    // Appel AJAX
    $.ajax({
        url: './ActionServlet',
        method: 'POST',
        data: {
            todo: 'getIntervention'
        },
        dataType: 'json'
    })
    .done( function (response) { // Fonction appelée en cas d'appel AJAX réussi
        console.log('Response',response); // LOG dans Console Javascript
        if (response.valide) {
            // // TODO: afficher les informations de l'élève
            $('#nomEleve').html(response.intervention.prenom + " " + response.intervention.nom); // Message pour le paragraphe de notification
            $('#nomEtablissement').html(response.intervention.etablissement) ; 
            $('#niveauScolaire').html(response.intervention.classe) ; 
            $('#matiereEnseignee').html(response.intervention.matiere) ;
            $('#detailsDemande').html(response.intervention.details) ;
            $('#accueilIntervenant').css('display', 'block');
        }
        else {
            $('#aucuneDemande').css('display', 'block');
            //on désactive le bouton de soutien
            $('#boutonSoutien').prop('disabled', true);
            $('#boutonSoutien').css('background-color', 'darkgray');
            $('#boutonSoutien').css('color', 'gray');
            $('#boutonSoutien').css('cursor', 'not-allowed');
        }
    })
    .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
        console.log('Error',error); // LOG dans Console Javascript
        alert("Erreur lors de l'appel AJAX");
    });
}

$(document).ready( function () {
    $('#boutonDeconnexion').on( 'click', function () { // Fonction appelée lors du clic sur le bouton
        // Appel AJAX
        $.ajax({
            url: './ActionServlet',
            method: 'POST',
            data: {
                todo: 'deconnecter'
            },
            dataType: 'json'
        })
        .done( function (response) { // Fonction appelée en cas d'appel AJAX réussi
            console.log('Response',response); // LOG dans Console Javascript
            if (response.deconnecter) {
                window.location.href = "index.html";
            }
            else {
                alert("Erreur lors de la déconnexion");
            }
        })
        .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
            console.log('Error',error); // LOG dans Console Javascript
            alert("Erreur lors de l'appel AJAX");
        });
    });
});




document.addEventListener('DOMContentLoaded', function() {
    afficherInformationsIntervenant();
    afficherInformationsIntervention();
});

$(document).ready( function () {
    $('#boutonSoutien').on( 'click', function () { // Fonction appelée lors du clic sur le bouton
        window.location.href = "visioIntervenant.html";
    });
});
 
$(document).ready( function () {
    $('#boutonTableau').on( 'click', function () { // Fonction appelée lors du clic sur le bouton
        window.location.href = "tableauDeBordIntervenant.html" ;
    });
});
