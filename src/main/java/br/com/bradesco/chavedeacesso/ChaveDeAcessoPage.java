package br.com.bradesco.chavedeacesso;

import com.bradesco.core.exception.BradescoElementNotFoundException;
import com.bradesco.core.exception.BradescoException;
import com.bradesco.core.factory.EngineFactory;
import com.bradesco.core.factory.MobileEngine;
import com.bradesco.core.internal.ReporterHelper;
import com.bradesco.core.sdk.BradMobileDriver;
import com.bradesco.core.sdk.page_object.PageObject;
import com.bradesco.core.sdk.page_object.PageObjectElement;
import com.bradesco.core.sdk.page_object.PageObjectInitializer;
import com.bradesco.core.sdk.page_object.annotations.AndroidCCSFindBy;
import com.bradesco.core.util.GlobalDriver;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ChaveDeAcessoPage implements PageObject {
    private BradMobileDriver mobiledriver;
    private final Logger logger = Logger.getLogger(ChaveDeAcessoPage.class.getName());

    // ----- INÍCIO: CONSTRUTOR -----
    public ChaveDeAcessoPage(BradMobileDriver mobiledriver) {
        this.mobiledriver = mobiledriver;
        PageObjectInitializer.initializeAllMobileFields(mobiledriver, Duration.ofSeconds(10), this);
    }
    // ----- FIM: CONSTRUTOR -----

    @AndroidCCSFindBy(text = "teste")
    //@IOSCCSFindBy (name = "ndo22")
    private PageObjectElement minhaChave;

    @AndroidCCSFindBy(resourceId = "br.com.bradesco.netempresa:id/btn_home_token")
    //@IOSCCSFindBy(accessibility = "home_token")
    private PageObjectElement botaoChaveDeSeguranca;

    @AndroidCCSFindBy(resourceId = "br.com.bradesco.netempresa:id/bottomSheetConfirm")
    //@IOSCCSFindBy(accessibility = "br.com.bradesco.netempresa:id/bottomSheetConfirm")
    private PageObjectElement botaoFechar;

    @AndroidCCSFindBy(resourceId = "br.com.bradesco.netempresa:id/login_pin_field")
    //@IOSCCSFindBy(accessibility = "pin_chave_bg")
    private PageObjectElement campoPin;

    @AndroidCCSFindBy(resourceId = "br.com.bradesco.netempresa:id/ok_button")
    //@IOSCCSFindBy(name = "ok enable")
    private PageObjectElement botaoOk;

    @AndroidCCSFindBy(resourceId = "br.com.bradesco.netempresa:id/otp_field")
    //@IOSCCSFindBy(name = "dispositivo")
    private PageObjectElement otpField;

    @AndroidCCSFindBy(resourceId = "br.com.bradesco.netempresa:id/btn_skip")
    PageObjectElement botaoPular;

    @AndroidCCSFindBy(resourceId = "br.com.bradesco.netempresa:id/rating_no")
    PageObjectElement btnNaoObrigado;


    public String obterChaveDeAcesso() throws BradescoException {

        mobiledriver = GlobalDriver.get();

        ReporterHelper.report("Tela inicial app", mobiledriver.getDriver());

        if (!botaoChaveDeSeguranca.isVisible()){
            clickIfDisplayed(botaoPular); // tutorial do app quando abre pela 1a vez
            clickIfDisplayed(botaoFechar); // modal "Fique atento"
            clickIfDisplayed(btnNaoObrigado);
        }

        clickIfDisplayed(botaoChaveDeSeguranca); // chave de segurança da homepage
        clickIfDisplayed(botaoFechar); // modal "Fique atento" após clicar em chave de segurança da homepage

        // tenta inserir o PIN pra caso exista apenas uma única chave cadastrada, senão procura pela chave antes
        try {
            campoPin.type("1212");
        } catch (BradescoElementNotFoundException e) {
            minhaChave.click();
        }

        // insere o PIN
        campoPin.type("1212");
        botaoOk.click();

        // aguarda o otp ficar visível
        otpField.exists(Duration.ofSeconds(60));
        String otp = otpField.getText();
        ReporterHelper.report("Chave de segurança", mobiledriver.getDriver());
        logger.info("OTP: " + otp);

        return otp;
    }

    protected void clickIfDisplayed(PageObjectElement element) {
        try {
            if (element.isVisible()) element.click();
        } catch (NoSuchElementException | BradescoException ignored) {
        }
    }

    public String getOtp() throws BradescoException {

        String otp = mobiledriver.getDriver().findElement(By.id("otp_field")).getText();
        ReporterHelper.report("OTP Confirmação", mobiledriver.getDriver());
        logger.info("OTP confirmação: " + otp);

        return otp;
    }

}

