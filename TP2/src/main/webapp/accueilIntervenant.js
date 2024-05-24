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
        }
        else {
            $('#nomEleve').html("Jsp"); // Message pour le paragraphe de notification
        }
    })
    .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
        console.log('Error',error); // LOG dans Console Javascript
        alert("Erreur lors de l'appel AJAX");
    });
}




document.addEventListener('DOMContentLoaded', function() {
    afficherInformationsIntervenant();
    afficherInformationsIntervention();
});

$(document).ready( function () {
    $('#boutonVisio').on( 'click', function () { // Fonction appelée lors du clic sur le bouton
        window.location.href = "visioEleve.html";
    });
});
        
$(document).ready( function () {
    $('#boutonDeconnexion').on( 'click', function () { // Fonction appelée lors du clic sur le bouton
        window.location.href = "index.html" ;
    });
});
        
