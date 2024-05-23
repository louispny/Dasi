/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready( function () {
                $('#login').on( 'click', function () { // Fonction appelée lors du clic sur le bouton

                    console.log("clic sur le bouton de connexion"); // LOG dans Console Javascript
                    $('#messageErreurText').html("Connexion..."); // Message pour le paragraphe de notification

                    // Récupération de la valeur des champs du formulaire
                    var champLogin = $('#email').val();
                    var champPassword = $('#password').val();

                    // Appel AJAX
                    $.ajax({
                        url: './ActionServlet',
                        method: 'POST',
                        data: {
                            todo: 'connecter',
                            login: champLogin,
                            password: champPassword
                        },
                        dataType: 'json'
                    })
                    .done( function (response) { // Fonction appelée en cas d'appel AJAX réussi
                        console.log('Response',response); // LOG dans Console Javascript
                        if (response.connexion) {
                            // // TODO: afficher les informations du client dans la notification
                            // Exemple: Connexion de Ada Lovelace (ID 1)
                            window.location.href = response.utilisateur.redirectURL ; 
                        }
                        else {
                            $('#messageErreurText').html("Erreur de Connexion"); // Message pour le paragraphe de notification
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

function goToInscription() {
    window.location.href = 'inscription.html';
}