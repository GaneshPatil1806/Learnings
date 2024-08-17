
const prisma = require('../prisma/index');
const cookieToken = require('../helpers/getCookie');

exports.signup = async (req, res) => {

    //console.log(req.body)
    try {
        const { name, email, password } = req.body;

        if (!name || !email || !password) {
            return res.status(400).json({ error: 'All fields are required.' });
        }

       // console.log(req.body)

        const user = await prisma.user.create({
            data: {
                name,
                email,
                password
            }
        });
        
        console.log(user);

        cookieToken(user, res);

    } catch (e) {
        res.status(500).json({ error: e.message });
    }
};