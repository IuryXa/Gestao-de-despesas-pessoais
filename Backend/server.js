const express = require('express');
const mysql = require('mysql2');

const app = express();
const port = process.env.PORT || 443;

// Conexão MySQL
const db = mysql.createConnection({
  host: process.env.host,
  user: process.env.user, 
  password: process.env.password, 
  database: process.env.database,
  ssl:{
    rejectUnauthorized: false
}
});

db.connect((err) => {
  if (err) {
    console.error('Erro ao conectar a database', err);
    return;
  }
  console.log('Conectado a Database MySQL');
});

app.use(express.json()); // Json para POST

app.post("/usuario", function (req, res) {
    console.log(req.body);
    var nome = req.body.nome;
    var senha = req.body.senha;
    var email = req.body.email;
    console.log(nome + " " + senha + " " + email + " " + celular);
    var query = 'INSERT INTO USUARIO (nome, senha, email, telefone) VALUES (?, ?, ?, ?)';
    db.query(query, [nome, senha, email, celular], (err, result) => {
      if (err) {
        console.error('Falha ao adicionar usuario', err);
        return res.status(500).json({ error: 'Falha ao adicionar usuario' + err.message });
      }
      res.status(201).json({ message: 'Usuario adicionado' });
    });
  });

app.listen(port, () => {
    console.log(`Server running on https://uberproject-cjajhtamdkemhqd2.canadacentral-01.azurewebsites.net:${port}`);
  });