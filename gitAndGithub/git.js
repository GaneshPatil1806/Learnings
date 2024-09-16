function add (){
    return 1+2;
}

function multiply (){
    return 1*2;
}


function generatePassword(){

    const temp="1234567890qwertyuiopasdfghjklzxcvbnm,./';][=`";

    let pass="";
    for(let i=0;i<10;i++){
        let num=(Math.random())*100;
        num=Math.floor(num)%temp.length;
        // console.log(num)
        pass+=temp[num];
    }
    
    return pass;
}

console.log("GitHub done");
console.log(generatePassword());