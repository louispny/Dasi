/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready( function () {
                $('#signup').on( 'click', function () { // Fonction appelée lors du clic sur le bouton

                    console.log("clic sur le bouton d'inscription"); // LOG dans Console Javascript
                    $('#messageErreurText').html("Inscription..."); // Message pour le paragraphe de notification

                    // Récupération de la valeur des champs du formulaire
                    var champNom = $('#nom').val(); 
                    var champPrenom = $('#prenom').val(); 
                    var champClasse = $('#classe').val() ; 
                    var champDateDeNaissance = $('#dateNaissance').val() ;
                    var champCodeEtablissement = $('#code').val() ; 
                    var champEmail = $('#email').val();
                    var champPassword = $('#password').val();
                    var champPassword2 = $('#password2').val() ; 
                    
                    if (champPassword !== champPassword2) {
                        $('#messageErreurText').text("Les mots de passe ne correspondent pas").show();
                        return;
                    } else {
                        $('#messageErreurText').text("").show();
                    }
                    // Appel AJAX
                    $.ajax({
                        url: './ActionServlet',
                        method: 'POST',
                        data: {
                            todo: 'inscrire',
                            nom: champNom,
                            prenom: champPrenom,
                            classe: champClasse,
                            dateNaissance: champDateDeNaissance,
                            code: champCodeEtablissement,
                            email: champEmail,
                            password: champPassword,
                            password2: champPassword2
                        },
                        dataType: 'json'
                    })
                    .done( function (response) { // Fonction appelée en cas d'appel AJAX réussi
                        console.log('Response',response); // LOG dans Console Javascript
                        if (response.inscription) {
                            $('#messageErreurText').html("Inscription "+ response.eleve.prenom+ " "+ response.eleve.nom + " (ID "+ response.eleve.id+")Classe : " + response.eleve.classe);  // Message pour le paragraphe de notification
                            // TODO: afficher les informations du client dans la notification
                            // Exemple: Connexion de Ada Lovelace (ID 1)
                            goToURL('accueilEleve.html')  ;
                        }
                        else {
                            $('#messageErreurText').html("Erreur d'inscription"); // Message pour le paragraphe de notification
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
            
function goToURL(url) {
    window.location.href = url ;
}