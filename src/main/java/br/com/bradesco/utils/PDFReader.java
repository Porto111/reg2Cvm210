package br.com.bradesco.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PDFReader {

    public String getTextoPDF(String pdfUrl) throws IOException {

        try {

            // Criar um objeto URL a partir da String da URL do PDF
            URL url = new URL(pdfUrl);

            // Abrir conexão HTTP
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            // Verificar se a conexão foi bem-sucedida (código de resposta 200)
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                // Carregar o PDF
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                PDDocument pdDocument = PDDocument.load(bufferedInputStream);

                // Ler o conteúdo do PDF
                PDFTextStripper pdfStripper = new PDFTextStripper();
                String pdfText = pdfStripper.getText(pdDocument);

                // Fechar recursos
                pdDocument.close();
                bufferedInputStream.close();
                inputStream.close();

                return pdfText;

            } else {

                // Caso a resposta não seja OK, trata o erro aqui
                System.out.println("Erro ao obter o PDF. Código de resposta: " + httpURLConnection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }         return null; // Retorna null em caso de erro
    }

    public void pdfNumeroDePaginas(String pdfUrl, int numPaginas) throws IOException {

        // Criar um objeto URL a partir da String da URL do PDF
        URL url = new URL(pdfUrl);

        // Carregar o PDF
        InputStream ip = url.openStream();
        BufferedInputStream bf = new BufferedInputStream(ip);
        PDDocument pdDocument = PDDocument.load(bf);

        // Armazenar o numero de paginas do PDF
        int pageCount = pdDocument.getNumberOfPages();
        System.out.println("Numero de paginas do PDF: " + pageCount);

        Assert.assertEquals("Valida numero de paginas do PDF", numPaginas, pageCount);

    }

    public void pdfTextoPorPagina(String pdfUrl, String texto, int pagina) throws IOException {

        // Criar um objeto URL a partir da String da URL do PDF
        URL url = new URL(pdfUrl);

        // Carregar o PDF
        InputStream ip = url.openStream();
        BufferedInputStream bf = new BufferedInputStream(ip);
        PDDocument pdDocument = PDDocument.load(bf);

        // Ler o conteúdo do PDF
        PDFTextStripper pdfStripper = new PDFTextStripper();
        pdfStripper.setStartPage(pagina);
        String pdfText = pdfStripper.getText(pdDocument);

        Assert.assertTrue("Valida texto do PDF em pagina especifica",
                pdfText.contains(texto));

    }

    public void pdfValidaTextoDiretorioLocal(String pdfDiretorioLocal, String texto) throws IOException {

        // Criar um objeto URL a partir da String da URL do PDF
        URL url = new URL(pdfDiretorioLocal);

        // Carregar o PDF
        InputStream ip = url.openStream();
        BufferedInputStream bf = new BufferedInputStream(ip);
        PDDocument pdDocument = PDDocument.load(bf);

        // Ler o conteúdo do PDF
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String pdfText = pdfStripper.getText(pdDocument);

        Assert.assertTrue("Valida texto do PDF",
                pdfText.contains(texto));

    }
}

