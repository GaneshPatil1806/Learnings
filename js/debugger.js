function sub(x,y){

    x+=2;
    y+=2;
    console.log("IN sub");
    debugger
    

    return x-y;
}

function add(x,y){

    console.log(sub(x,y));
    console.log("IN add");
    debugger

    return x+y;
}

console.log("Main");
console.log(add(3,4))