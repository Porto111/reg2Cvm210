package br.com.bradesco.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;


public class LoginAPI {

    public static String realizarRequisicao(String cpf, String cnpj) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        String vrScrambled = null;

        final RequestConfig requestConfig = RequestConfig.custom()
                .setContentCompressionEnabled(true)
                .setConnectionRequestTimeout(100000)
                .setSocketTimeout(100000).build();

        HttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();

        HttpPost httpPost = new HttpPost ("https://run.mocky.io/v3/f3a6ff29-a006-4f27-80e3-0348dc8e3142");
        //HttpPost httpPost = new HttpPost("https://www.ne12.bradesconetempresa.tu.teste.internet/descomplicati-srv-sessao/api/sessao/v1/desktop");

        // Adiciona o cabeçalho Content-Type para indicar que está enviando JSON
        httpPost.setHeader(new BasicHeader("Content-Type", "application/json"));

        // Define o payload
        String payload = "{ \"cpf\": \"" + cpf + "\", \"cnpj\": \"" + cnpj + "\", \"dispositivo\": { \"codigo\": null, \"numero\": null } }";
        StringEntity entity = new StringEntity(payload, "UTF-8");
        httpPost.setEntity(entity);

        try {
            // Executa a solicitação e obtém a resposta
            HttpResponse response = httpClient.execute(httpPost);

            // Obtém o corpo da resposta
            HttpEntity responseEntity = response.getEntity();
            BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));

            // Lê e imprime a resposta
            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }

            // Extrai o valor do campo "vrScrambled" do corpo da resposta aqui
            ObjectMapper objectMapper = new ObjectMapper();
            loginDescomplicaTI responseModel = objectMapper.readValue(responseBody.toString(), loginDescomplicaTI.class);

            System.out.println("RESPONSE MODEL: " + responseModel);

            // Acesse o valor de "vrScrambled" a partir do objeto responseModel
            vrScrambled = responseModel.getVrScrambled();

            // Retorna o valor de "vrScrambled"
            return vrScrambled;

            } catch (IOException e) {
                e.printStackTrace();
                // Em caso de erro, retorna null
                return null;
            }
    }

    public static class loginDescomplicaTI {
        @JsonProperty("cnpj")
        private String cnpj;

        @JsonProperty("nmEmpresa")
        private String nmEmpresa;

        @JsonProperty("cpf")
        private String cpf;

        @JsonProperty("nmProcurador")
        private String nmProcurador;

        @JsonProperty("cdPerfil")
        private int cdPerfil;

        @JsonProperty("nmPerfil")
        private String nmPerfil;

        @JsonProperty("vrScrambled")
        private String vrScrambled;

        @JsonProperty("url")
        private String url;

        // Construtores, getters e setters

        @Override
        public String toString() {
            return "realizarRequisicao{" +
                    "cnpj='" + cnpj + '\'' +
                    ", nmEmpresa='" + nmEmpresa + '\'' +
                    ", cpf='" + cpf + '\'' +
                    ", nmProcurador='" + nmProcurador + '\'' +
                    ", cdPerfil=" + cdPerfil +
                    ", nmPerfil='" + nmPerfil + '\'' +
                    ", vrScrambled='" + vrScrambled + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }

        public String getCnpj() {
            return cnpj;
        }

        public void setCnpj(String cnpj) {
            this.cnpj = cnpj;
        }

        public String getNmEmpresa() {
            return nmEmpresa;
        }

        public void setNmEmpresa(String nmEmpresa) {
            this.nmEmpresa = nmEmpresa;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public String getNmProcurador() {
            return nmProcurador;
        }

        public void setNmProcurador(String nmProcurador) {
            this.nmProcurador = nmProcurador;
        }

        public int getCdPerfil() {
            return cdPerfil;
        }

        public void setCdPerfil(int cdPerfil) {
            this.cdPerfil = cdPerfil;
        }

        public String getNmPerfil() {
            return nmPerfil;
        }

        public void setNmPerfil(String nmPerfil) {
            this.nmPerfil = nmPerfil;
        }

        public String getVrScrambled() {
            return vrScrambled;
        }

        public void setVrScrambled(String vrScrambled) {
            this.vrScrambled = vrScrambled;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}

