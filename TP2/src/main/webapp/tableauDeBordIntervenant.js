/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function buildBarChart(container, graphData) {

    Highcharts.chart(container, {

        chart: {
            type: 'column'
        },
        title: {
            text: 'Répartition des cours donnés ce mois-ci par niveau de classe'
        },
        xAxis: {
            categories: graphData.labels
        },
        yAxis: {
            title: {
                text: 'Durée (minutes)'
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
function buildPieChart(container, graphData) {

    Highcharts.chart(container, {

        chart: {
            type: 'pie'
        },
        title: {
            text: "Temps de cours données ce mois-ci"
        },
        credits: {
            enabled: false
        },
        series: [{name: graphData.label, data: graphData.data}]
    });
}

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
            
            const filteredIndices = response.DureeSoutienNiveau.map((value, index) => (value !== 0 ? index : -1)).filter(index => index !== -1);
            var lineChartData = {
                labels: filteredIndices.map(index => response.ClasseSoutienNiveau[index]),
                data: filteredIndices.map(index => response.DureeSoutienNiveau[index])
            };
            const combinedArray = response.ClasseSoutienMatiere.map((name, index) => {
                return {
                    name: name,
                    y: response.DureeSoutienMatiere[index]
                };
              
            }).filter(item => item.y !== 0);
            var proportionData = {label : "Nombre de minutes de cours données ce mois-ci" , data : combinedArray}
            buildBarChart('container-1', lineChartData);
            buildPieChart('container-2', proportionData) ; 
            console.log(combinedArray) ; 
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

$(document).ready( function () {
    $('#historique').on( 'click', function () { // Fonction appelée lors du clic sur le bouton
        window.location.href = "historiqueIntervenant.html" ;
    });
});

$(document).ready( function () {
    $('#retour').on( 'click', function () { // Fonction appelée lors du clic sur le bouton
        window.location.href = "accueilIntervenant.html" ;
    });
});
