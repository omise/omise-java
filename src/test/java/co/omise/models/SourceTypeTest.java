package co.omise.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SourceTypeTest {

    @Test
    public void checkStringValue() {
        assertEquals("internet_banking_bay", SourceType.InternetBankingBay.toString());
        assertEquals("internet_banking_ktb", SourceType.InternetBankingKtb.toString());
        assertEquals("internet_banking_bbl", SourceType.InternetBankingBbl.toString());
        assertEquals("internet_banking_scb", SourceType.InternetBankingScb.toString());
        assertEquals("installment_uob", SourceType.InstallmentUob.toString());
        assertEquals("installment_ttb", SourceType.InstallmentTtb.toString());
        assertEquals("mobile_banking_bay", SourceType.MobileBankingBay.toString());
        assertEquals("mobile_banking_bbl", SourceType.MobileBankingBbl.toString());
        assertEquals("mobile_banking_kbank", SourceType.MobileBankingKbank.toString());
        assertEquals("mobile_banking_ocbc_pao", SourceType.MobileBankingOcbcPao.toString());
        assertEquals("mobile_banking_scb", SourceType.MobileBankingScb.toString());
        assertEquals("bill_payment_tesco_lotus", SourceType.BillPaymentTescoLotus.toString());
        assertEquals("alipay", SourceType.Alipay.toString());
        assertEquals("barcode_alipay", SourceType.BarcodeAlipay.toString());
        assertEquals("econtext", SourceType.Econtext.toString());
        assertEquals("truemoney", SourceType.TrueMoney.toString());
        assertEquals("promptpay", SourceType.PromptPay.toString());
        assertEquals("fpx", SourceType.Fpx.toString());
        assertEquals("rabbit_linepay", SourceType.RabbitLinepay.toString());
        assertEquals("alipay_cn", SourceType.AlipayCN.toString());
        assertEquals("alipay_hk", SourceType.AlipayHK.toString());
        assertEquals("dana", SourceType.DANA.toString());
        assertEquals("gcash", SourceType.GCash.toString());
        assertEquals("kakaopay", SourceType.KakaoPay.toString());
        assertEquals("touch_n_go", SourceType.TouchNGo.toString());
        assertEquals("mobile_banking_ktb", SourceType.MobileBankingKtb.toString());
        assertEquals("atome", SourceType.Atome.toString());
        assertEquals("duitnow_obw", SourceType.DuitNowOBW.toString());
        assertEquals("wechat_pay", SourceType.WeChatPay.toString());
    }
}
