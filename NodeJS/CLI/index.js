const { Command } = require('commander');
const program = new Command();

const fs = require('fs');

program
  .command('count')
  .argument('<file>')
  .description('Counts number of lines')
  .action((file) => {
    fs.readFile(file,'utf-8',(err,data)=>{
        if(err){
            console.log(err)
        }else{
            console.log('There are',data.split('\n').length,'lines in the file.');
            console.log('data:\n',data);
        }
    });
  });

  program.parse();