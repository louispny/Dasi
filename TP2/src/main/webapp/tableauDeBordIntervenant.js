/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

document.addEventListener('DOMContentLoaded', () => {
    
    chargerStats1() ;
});

function chargerStats1() {
    console.log("récupération des stats1"); // LOG dans Console Javascript

    // Appel AJAX
    $.ajax({
        url: './ActionServlet',
        method: 'POST',
        data: {
            todo: 'stats'
        },
        dataType: 'json'
    })
    .done( function (response) { // Fonction appelée en cas d'appel AJAX réussi
        console.log('Response',response); // LOG dans Console Javascript
        if (response.valide) {
            console.log('hess') ;
            // // TODO: afficher les informations de l'élève
            // $('#nomIntervenant').html(response.utilisateur.prenom + " " + response.utilisateur.nom); // Message pour le paragraphe de notification
            
            buildBarChart('container-1', lineChartData);
        }
        else {
            // $('#nomIntervenant').html("Jsp"); // Message pour le paragraphe de notification
        }
    })
    .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
        console.log('Error',error); // LOG dans Console Javascript
        alert("Erreur lors de l'appel AJAX");
    });
}

function buildBarChart(container, graphData) {

                Highcharts.chart(container, {

                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: 'Température en Amphi'
                    },
                    subtitle: {
                        text: 'Source: a priori'
                    },
                    xAxis: {
                        categories: graphData.labels
                    },
                    yAxis: {
                        title: {
                            text: 'Température (°C)'
                        }
                    },
                    legend: {
                        enabled: false
                    },
                    credits: {
                        enabled: false
                    },
                    series: [{name: 'Données', data: graphData.data}]
                });
}
$(document).ready( function () {
    $('#historique').on( 'click', function () { // Fonction appelée lors du clic sur le bouton
        window.location.href = "historiqueIntervenant.html" ;
    });
});
