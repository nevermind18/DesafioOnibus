banco de dados
Linha 1 <- n Itinerario

Apis

Itinerario API
uri base = /v1/itinerario

@PostMapping("/")
necessita de um Itinerario para cadastra um Itinerario e retorna um ResponseEntity<Itinerario> 

@GetMapping("/")
busca todos os Itinerarios e retorna uma List<Itinerario>

@GetMapping("/{id}")
buscar um Itinerario por um id recebido retorna um ResponseEntity<Itinerario>

@PutMapping("/")
necessita de um Itinerario para alterar um Itinerario já existente e retorna ResponseEntity<Itinerario>
  
@DeleteMapping("/")
Deleta um itinerario usando um itinerario recebido 
  
Linha API

uri base = /v1/linha
  
@PostMapping("/consumirApi")
consome uma api que retorna uma List<Linha> 
  
@PostMapping("/")
necessita de uma linha para cadastrar e retorna uma ResponseEntity<Linha>

@GetMapping("/")
busca todas as linhas e retorna uma List<Linha>

@GetMapping("/{id}")
buscar um linha por um id recebido e retorna um ResponseEntity<linha>

@PutMapping("/")
necessita de uma linha para alterar uma linha já existente e retorna uma ResponseEntity<Linha>

@DeleteMapping("/")
Deleta uma linha usando uma linha recebida 

@GetMapping("/nome/{nome}")
buscar um linha por um nome recebido e retorna uma ResponseEntity<linha>

@GetMapping("/distancia")
necessita de uma list<> para buscar um linha que passe dentro de uma area e retorna uma ResponseEntity<linha>
