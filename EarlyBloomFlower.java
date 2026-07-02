/**
 * 遺伝子A：他の花より早く開花する花。
 */
public class EarlyBloomFlower extends Flower {

    /**
     * 早咲きの花を生成する。
     */
    public EarlyBloomFlower() {
        this.name     = "早咲きの花";
        this.geneCode = "遺伝子A";
    }

    /**
     * 将来の行動を実行する。
     * 
     * 基本行動として早咲きを行い、日照時間に応じて花のサイズを変える。
     * 
     */
    @Override
    public void futureBehavior() {
        bloomEarly(); // ① 遺伝子による基本行動

        // ② 環境要因（日照時間）による度合いの調整
        if (environment.getSunlightHours() >= 8) {
            System.out.println("日照時間が十分（" + environment.getSunlightHours() + "h） → 巨大な花を咲かせます");
        } else {
            System.out.println("日照時間が短い（" + environment.getSunlightHours() + "h） → 通常サイズで咲きます");
        }
    }

    /**
     * 他の花より早く開花する（このクラス独自のメソッド。親クラスには存在しない）。
     */
    public void bloomEarly() {
        System.out.println("他の花より早く開花します");
    }
}
