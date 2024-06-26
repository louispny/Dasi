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

$('#boutonsMatieres').on('click', '.bouton3', function(event) {
        console.log("clic sur un bouton de matière"); // LOG dans Console Javascript
        console.log(event.target.id);
        $('.bouton3').css('background-color', 'darkgray');
        $('.bouton3').css('color', 'gray');
        $('.bouton4').css('background-color', '#020E36');
        $('.bouton4').css('color', 'white');
        $('#'+event.target.id).css('background-color', '#020E36');
        $('#'+event.target.id).css('color', 'white');

        matiere = event.target.id.substr(7);
        console.log(matiere);
});

$(document).ready( function () {
    $('#boutonSoutien').on( 'click', function () { // Fonction appelée lors du clic sur le bouton
        if (matiere == "") {
            alert("Veuillez choisir une matière");
        }
        else {
            console.log("clic sur le bouton de soutien"); // LOG dans Console Javascript
            //$('#messageErreurText').html("Inscription..."); // Message pour le paragraphe de notification

            // Récupération de la valeur des champs du formulaire
            let description = $('#explicationDemande').val();
            $('#messageAttente').html("Demande en cours de traitement...");
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
                
                $('#messageAttente').html("");
                if (response.valide) {
                    $('#demandeValidee').css('display', 'block');
                }
                else {
                    $('#demandeRefusee').css('display', 'block');
                }
                //on désactive tous les autres boutons
                $('.bouton1').prop('disabled', true);
                $('.bouton2').prop('disabled', true);
                $('.bouton3').prop('disabled', true);
                $('.bouton4').prop('disabled', true);
                //on enlève le curseur pointer
                $('.bouton1').css('cursor', 'default');
                $('.bouton2').css('cursor', 'default');
                $('.bouton3').css('cursor', 'default');
                $('.bouton4').css('cursor', 'default');
            })
            .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
                console.log('Error',error); // LOG dans Console Javascript
                alert("Erreur lors de l'appel AJAX");
            })
            .always( function () { // Fonction toujours appelée
                
            });
        }
    });
});

$(document).ready( function () {
    $('#boutonDeconnexion').on( 'click', function () { // Fonction appelée lors du clic sur le bouton
        console.log("clic sur le bouton de déconnexion"); // LOG dans Console Javascript
        //$('#messageErreurText').html("Déconnexion..."); // Message pour le paragraphe de notification

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
                $('#explicationDemande').html("Erreur de déconnexion"); // Message pour le paragraphe de notification
            }
        })
        .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
            console.log('Error',error); // LOG dans Console Javascript
            alert("Erreur lors de l'appel AJAX");
        });
    });
});


$(document).ready( function () {
    $('#boutonRetour').on( 'click', function () { // Fonction appelée lors du clic sur le bouton
        $('#demandeRefusee').css('display', 'none');
    });
});

$(document).ready( function () {
    $('#boutonVisio').on( 'click', function () { // Fonction appelée lors du clic sur le bouton
        window.location.href = "visioEleve.html";
    });
});
        
$(document).ready( function () {
    $('#boutonHisto').on( 'click', function () { // Fonction appelée lors du clic sur le bouton
        window.location.href = "historiqueEleve.html";
    });
});
            
