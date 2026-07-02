/**
 * 遺伝子D：強い香りを放つ花。
 */
public class FragrantFlower extends Flower {

    /**
     * 芳香の花を生成する。
     */
    public FragrantFlower() {
        this.name = "芳香の花";
        this.geneCode = "遺伝子D";
    }

    @Override
    public void futureBehavior() {
        releaseFragrance(); // ① 遺伝子による基本行動

        // ② 環境要因（水分量）による度合いの調整
        if (environment.getMoisture() >= 60) {
            System.out.println("水分量が十分（" + environment.getMoisture() + "%） → 香りがさらに強まります");
        } else {
            System.out.println("水分量が少ない（" + environment.getMoisture() + "%） → 香りが弱まり、しおれ気味です");
        }
    }

    /**
     * 強い香りを放つ（このクラス独自のメソッド。親クラスには存在しない）。
     */
    public void releaseFragrance() {
        System.out.println("強い香りを放ちます");
    }
}
