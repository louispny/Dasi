let matiere = "";

function afficherInformationsEleve() {
    // on récupére les informations de l'élève avec une requête AJAX
    // on récupére les informations de l'élève
    console.log("récupération des infos de l’élève"); // LOG dans Console Javascript

    // Appel AJAX
    $.ajax({
        url: './ActionServlet',
        method: 'POST',
        data: {
            todo: 'getEleve'
        },
        dataType: 'json'
    })
    .done( function (response) { // Fonction appelée en cas d'appel AJAX réussi
        console.log('Response',response); // LOG dans Console Javascript
        if (response.getEleve) {
            // // TODO: afficher les informations de l'élève
            $('#nomEleve').html(response.eleve.prenom + " " + response.eleve.nom); // Message pour le paragraphe de notification
            $('#classeEleve').html(response.eleve.classe); // Message pour le paragraphe de notification
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

function afficherBoutonsMatieres() {
    console.log("récupération des matières"); // LOG dans Console Javascript

    // Appel AJAX
    $.ajax({
        url: './ActionServlet',
        method: 'POST',
        data: {
            todo: 'getMatieres'
        },
        dataType: 'json'
    })
    .done( function (response) { // Fonction appelée en cas d'appel AJAX réussi
        console.log('Response',response); // LOG dans Console Javascript
        if (response.listeMatieres != null) {
            for (let i = 0; i < response.listeMatieres.length; i++) {
                if (i % 4 == 0) {
                    $('#boutonsMatieres').append('<div id="ligne' + (Math.floor(i/4)+1) + '" class="ligneBouton"></div>');
                }
                $('#ligne' + (Math.floor(i/4)+1)).append('<button class="bouton3" id="matiere' + response.listeMatieres[i].nom + '">' + response.listeMatieres[i].nom + '</button>');
                //$('#listeMatieres').append('<button class="btn btn-primary" id="matiere' + response.listeMatieres[i].id + '">' + response.listeMatieres[i].nom + '</button>');

            }
        }
        else {
            //$('#listeMatieres').html("Jsp"); // Message pour le paragraphe de notification
        }
    })
    .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
        console.log('Error',error); // LOG dans Console Javascript
        alert("Erreur lors de l'appel AJAX");
    });
}

document.addEventListener('DOMContentLoaded', function() {
    afficherInformationsEleve();
    afficherBoutonsMatieres();
});

document.addEventListener('click', function (event) {
    if (event.target.classList.contains('bouton3')) {
        console.log("clic sur un bouton de matière"); // LOG dans Console Javascript
        console.log(event.target.id);
        $('.bouton3').css('background-color', 'gray');
        event.target.style.backgroundColor = 'blue';
        matiere = event.target.id.substr(7);
        console.log(matiere);
    }
});

$(document).ready( function () {
    $('#boutonSoutien').on( 'click', function () { // Fonction appelée lors du clic sur le bouton

        console.log("clic sur le bouton de soutien"); // LOG dans Console Javascript
        //$('#messageErreurText').html("Inscription..."); // Message pour le paragraphe de notification

        // Récupération de la valeur des champs du formulaire
        let description = $('#explicationDemande').val();
        // Appel AJAX
        $.ajax({
            url: './ActionServlet',
            method: 'POST',
            data: {
                todo: 'demandeIntervention',
                matiere: matiere,
                detail: description
            },
            dataType: 'json'
        })
        .done( function (response) { // Fonction appelée en cas d'appel AJAX réussi
            console.log('Response',response); // LOG dans Console Javascript
            if (response.valide) {
                $('#demandeValidee').css('display', 'true');
            }
        })
        .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
            console.log('Error',error); // LOG dans Console Javascript
            alert("Erreur lors de l'appel AJAX");
        })
        .always( function () { // Fonction toujours appelée
            
        });
    });
});