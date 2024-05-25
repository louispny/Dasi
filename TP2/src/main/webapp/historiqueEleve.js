document.addEventListener('DOMContentLoaded', () => {
    const historiqueItems = document.querySelectorAll('.historique-item');
    const detailsContainer = document.querySelector('.details-container .details-item');
    const retourButton = document.querySelector('.retour');

    historiqueItems.forEach(item => {
        item.addEventListener('click', () => {
            const matiere = item.getAttribute('data-matiere');
            const intervenant = item.getAttribute('data-intervenant');
            const duree = item.getAttribute('data-duree');
            const note = item.getAttribute('data-note');
            const bilan = item.getAttribute('data-bilan');

            // Met à jour les détails
            detailsContainer.querySelector('.matiere').textContent = matiere;
            detailsContainer.querySelector('.intervenant').textContent = intervenant;
            detailsContainer.querySelector('.duree').textContent = duree;
            detailsContainer.querySelector('.bilan p').textContent = bilan;

            // Met à jour les étoiles
            const noteContainer = detailsContainer.querySelector('.note');
            noteContainer.innerHTML = '';
            for (let i = 0; i < 5; i++) {
                const star = document.createElement('span');
                star.classList.add(i < note ? 'etoile' : 'etoile-vide');
                star.textContent = i < note ? '★' : '☆';
                noteContainer.appendChild(star);
            }
        });
    });

    retourButton.addEventListener('click', () => {
        detailsContainer.querySelector('.matiere').textContent = '';
        detailsContainer.querySelector('.intervenant').textContent = '';
        detailsContainer.querySelector('.duree').textContent = '';
        detailsContainer.querySelector('.bilan p').textContent = '';
        detailsContainer.querySelector('.note').innerHTML = '';
    });
});
 function importerHistorique() {
    // on récupére les informations de l'élève avec une requête AJAX
    // on récupére les informations de l'élève
    console.log("récupération de l'historique de l’élève"); // LOG dans Console Javascript

    // Appel AJAX
    $.ajax({
        url: './ActionServlet',
        method: 'POST',
        data: {
            todo: 'getHistoriqueEleve'
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
    importerHistorique() ; 
});