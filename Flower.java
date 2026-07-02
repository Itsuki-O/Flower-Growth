/**
 * 花を表す抽象クラス。
 */
public abstract class Flower {

    /**
     * 遺伝子コード（例："遺伝子A" など）
     */
    protected String geneCode;

    /**
     * 花の名前（表示用）
     */
    protected String name;

    /**
     * その花が置かれている環境情報
     */
    protected Environment environment;

    /**
     * 花を生成する。（コンストラクタ）
     */
    protected Flower() {
    }

    /**
     * 花の名前を応答する。
     * 
     * @return 花の名前
     */
    public String getName() {
        return name;
    }

    /**
     * 遺伝子コードを応答する。
     * 
     * @return 遺伝子コード
     */
    public String getGeneCode() {
        return geneCode;
    }

    /**
     * 環境情報を応答する。
     * 
     * @return 花が置かれている環境情報
     */
    public Environment getEnvironment() {
        return environment;
    }

    /**
     * 環境情報を設定する。
     * 
     * @param environment 設定する環境情報
     */
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 成長する。
     */
    public void grow() {
        System.out.println("[" + name + "] すくすくと成長しています。");
    }

    /**
     * 開花する。
     */
    public void bloom() {
        System.out.println("[" + name + "] 花が咲きました。");
    }

    /**
     * 将来の行動抽象メソッド
     */
    public abstract void futureBehavior();

    /**
     * 花の機能を実現する。
     * 
     * 成長・開花・将来の行動を、この順番で実行する。
     * 
     */
    public void perform() {
        grow();
        bloom();
        futureBehavior();
    }
}
