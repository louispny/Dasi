let nbInterventions = 0;

document.addEventListener('DOMContentLoaded', () => {
    
    importerHistorique() ;

   
});

function importerHistorique() {
    // on récupére les informations de l'élève avec une requête AJAX
    // on récupére les informations de l'élève
    console.log("récupération de l'historique de l’intervenant"); // LOG dans Console Javascript

    // Appel AJAX
    $.ajax({
        url: './ActionServlet',
        method: 'POST',
        data: {
            todo: 'getHistoriqueIntervenant'
        },
        dataType: 'json'
    })
    .done( function (response) { // Fonction appelée en cas d'appel AJAX réussi
        console.log('Response',response); // LOG dans Console Javascript
        var historiqueDiv = $('#historique-list');
        historiqueDiv.empty() ; 
        if (response.valide) {
            /*var interventionHtml = '<div class="intervention-item" id="item-1"' +
            // // TODO: afficher les informations de l'élève
            $('#nomEleve').html(response.eleve.prenom + " " + response.eleve.nom); // Message pour le paragraphe de notification
            $('#classeEleve').html(response.eleve.classe); // Message pour le paragraphe de notification*/
            for (let i = 0; i < response.listeIntervention.length; i++) {
                let laMatiere = response.listeIntervention[nbInterventions].matiere;
                let nomEleve = response.listeIntervention[nbInterventions].eleveNom;
                let prenomEleve = response.listeIntervention[nbInterventions].elevePrenom;
                let laDuree = response.listeIntervention[nbInterventions].duree;
                let laNote = response.listeIntervention[nbInterventions].note;
                let leBilan = response.listeIntervention[nbInterventions].bilan;
                $('#liste').append('<div id="item' + nbInterventions + '" class="historique-item" data-matiere="' + laMatiere + '" data-eleve="' + prenomEleve + " " + nomEleve + '" data-duree="' + laDuree + '" data-note="' + laNote + '" data-bilan="' + leBilan + '"></div>');
                $('#item' + nbInterventions).append('<div class="ligne" id="ligne' + nbInterventions + '"></div>');
                $('#ligne' + nbInterventions).append('<div class="matiereDiv">' + laMatiere + '</div>');
                $('#ligne' + nbInterventions).append('<div id="stars' + nbInterventions + '" class="starsDiv"></div>');
                for (let i = 1; i <= 5; i++) {
                    $('#stars' + nbInterventions).append('<svg id="starn' + i + '-' + nbInterventions + '" class="star" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path class="clic" id="star' + i + '-' + nbInterventions + '" d="M12 2l2.29 7.05h7.37l-5.98 4.36 2.29 7.05-6.02-4.36-6.02 4.36 2.29-7.05-5.98-4.36h7.37z" /><path d="M0 0h24v24h-24z" fill="none" /></svg>');
                    if (i <= laNote) {
                        $('#starn' + i + '-' + nbInterventions).css('fill', '#FFD700');
                    }
                }
                $('#item' + nbInterventions).append('<div class="eleveDiv" id="eleve' + nbInterventions + '"></div>');
                $('#eleve' + nbInterventions).append('<img src="images/etudiant.png" alt="profil" class="student" />');
                $('#eleve' + nbInterventions).append('<div class="eleveDenom">' + prenomEleve + " " + nomEleve + '</div>');
                $('#item' + nbInterventions).append('<div class="dureeDiv" id="duree' + nbInterventions + '"></div>');
                $('#duree' + nbInterventions).append('<img src="images/horloge.png" alt="horloge" class="clock" />');
                $('#duree' + nbInterventions).append('<div class="duree">' + laDuree + ' minutes</div>');
                nbInterventions++;
            }
            $('#titreHistorique').html("Historique des soutiens (" + nbInterventions + ")"); // Message pour le paragraphe de notification

            const historiqueItems = document.querySelectorAll('.historique-item');

            console.log('Historique Items:', historiqueItems);
        
            historiqueItems.forEach(item => {
                item.addEventListener('click', () => {
                    const matiere = item.getAttribute('data-matiere');
                    const eleve = item.getAttribute('data-eleve');
                    const duree = item.getAttribute('data-duree');
                    const note = item.getAttribute('data-note');
                    const bilan = item.getAttribute('data-bilan');
                    console.log('Matière:', matiere);
                    // Met à jour les détails
                    $('#detailMatiere').html(matiere);
                    $('#eleveNom').html(eleve);
                    $('#dureeText').html(duree + ' minutes');
                    $('#detailsBilan').html(bilan);
        
        
        
                    // Met à jour les étoiles
                    for (let i = 1; i <= 5; i++) {
                        $('#starn'+i).css('fill','gray');
                    }
                    for (let i = 1; i <= note; i++) {
                        $('#starn'+i).css('fill','#FFD700');
                    }
        
                    $('#detail').css('visibility', 'visible');
        
                });
            });

        }
        else {
            //$('#nomEleve').html("Jsp"); // Message pour le paragraphe de notification
        }
    })
    .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
        console.log('Error',error); // LOG dans Console Javascript
        alert("Erreur lors de l'appel AJAX");
    });
}

//bouton retour
$('.retour').on('click', () => {
    window.location.href = 'accueilIntervenant.html';
});