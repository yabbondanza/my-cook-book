import bg from './bg.svg'
import illustration from './illustration.svg';
import {Button, Container, Form, Toast} from "react-bootstrap";
import {useNavigate, Link} from "react-router-dom";
import "./Login.css"
import {useState} from "react";
import api from "../../services/api";

function LoginPage() {

    const [showA, setShowA] = useState(false);
    const toggleShowA = () => setShowA(!showA);
    const [senhaIncorretaError, setSenhaIncorretaError] = useState(false);
    const [emailNaoExisteError, setEmailNaoExisteError] = useState(false);



    const handleSideEffectLogin = (response) => {
        console.log("RESPOSTA LOGIN",response);
        if (response.data.message === "Usuário autorizado a entrar") {
            console.log("cheguei 1");
            localStorage.setItem("userID", response.data.id);
            console.log("cheguei 2");
            console.log(campoEmail, campoSenha);
            console.log("cheguei 3");
            navigate('/homepage');
        } else if (response.data.message === "Credenciais inválidas") {
            toggleShowA();
            setSenhaIncorretaError(true);
            setEmailNaoExisteError(false);
        } else if (response.data.message === "E-mail não existe") {
            toggleShowA();
            setEmailNaoExisteError(true);
            setSenhaIncorretaError(false);
        }


    }

    const navigate = useNavigate();
    const [campoEmail, setCampoEmail] = useState("");
    const [campoSenha, setCampoSenha] = useState("");


    const handleSubmit = (e) => {
        e.preventDefault();
        (campoEmail !== "" && campoSenha !== "") ?
            (api.post("/login", {email: campoEmail, senha: campoSenha})
            .then((response) => {
                handleSideEffectLogin(response)
            })
            .catch(err => {
                console.log("erro de autenticacao");
                console.log(err);
            })) : (console.log("em branco"))

    }


    return (
        <>
            <Toast className={"ms-auto mt-3 me-5 textoSansSerif"} show={showA} onClose={toggleShowA}>
                <Toast.Header>
                    <img
                        src="holder.js/20x20?text=%20"
                        className="rounded me-2"
                        alt=""
                    />
                    <strong className="me-auto">MyCookBook</strong>
                </Toast.Header>
                <Toast.Body>Não foi possível fazer o login!</Toast.Body>
            </Toast>

            <img src={bg} className={"img-bg"}/>
            <Container className={"position-absolute top-50 start-50 translate-middle"}>
                <div className={"row"}>
                    <div className={"col-md-6 d-flex align-items-center justify-content-center"}>
                        <img src={illustration}/>
                    </div>
                    <div className={"col-md-6 d-flex align-items-center justify-content-center coluna-cartao"}>
                        <div className={"card-login d-flex align-items-center"}>
                            <h1 className={"textoSerif textoBold"}>myCookBook</h1>
                            <Form className={"textoSansSerif"} onSubmit={handleSubmit}>
                                <Form.Group className="mb-4" controlId="formBasicEmail">
                                    <Form.Label>Email</Form.Label>
                                    <Form.Control type="email" placeholder="Email"
                                                  onChange={e => setCampoEmail(e.target.value)}/>
                                                      {emailNaoExisteError && (
                                                         <div className="error-message">E-mail não existe.</div>
                                                    )}
                                                  
                                </Form.Group>
                                <Form.Group className="mb-4" controlId="formBasicPassword">
                                    <Form.Label>Senha</Form.Label>
                                    <Form.Control type="password" placeholder="Senha"
                                                  onChange={e => setCampoSenha(e.target.value)}/>
                                                      {senhaIncorretaError && (
                                                             <div className="error-message">Senha incorreta.</div>
                                                        )}
                                </Form.Group>
                                <Form.Group className="mb-4  text-center" controlId="formBasicEnter">
                                    <Button type={"submit"} className={"botaoEntrar"}>
                                        Entrar
                                    </Button>
                                </Form.Group>
                            </Form>
                            <Link to={"/cadastro"} className={"btn btn-primary botaoCadastro textoSansSerif"}
                                  style={{textDecoration: 'none', color: 'inherit'}}>Não possuo cadastro</Link>
                        </div>
                    </div>
                </div>
            </Container>
        </>);
}

export default LoginPage;