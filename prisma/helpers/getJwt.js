const jwt = require('jsonwebtoken')

const getJwt = (id) => {

    return jwt.sign({id:id},process.env.JWT_SECRET,{expiresIn:'1 day'})
} 

module.exports = getJwt;