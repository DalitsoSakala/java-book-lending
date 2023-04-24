let sentences = []
function generate() {
    var data = {
    	"start": ["#NP# #VP#."],
    	"NP": ["#Det# #N#", "#Det# #N# that #VP#", "#Det# #Adj# #N#"],
    	"VP": ["#Vtrans# #NP#", "#Vintr#"],
    	"Det": ["The", "This", "That"],
    	"N": ["Mary Lungu", "Robert Phiri", "John Banda", "Brighton Nawa", "Jonathan Phiri", "Micheck Mbewe", "Simon Mumeno", "Chama Musonda"],
    	"Adj": ["cool", "lazy", "amazed", "sweet"],
    	"Vtrans": ["computes", "examines", "helps", "prefers", "sends", "plays with", "messes up with"],
    	"Vintr": ["coughs", "daydreams", "whines", "slobbers", "appears", "disappears", "exists", "cries", "laughs"]
    }
    
    let grammar = tracery.createGrammar(data);
    let expansion = grammar.flatten('#start#');

    sentences.push(expansion);

    printSentences(sentences);
}

function printSentences(sentences) {
    let textBox = document.getElementById("sentences");
    textBox.innerHTML = "";
    for(let i=sentences.length-1; i>=0; i--) {
        textBox.innerHTML += "<p>"+sentences[i]+"</p>"
    }
}
