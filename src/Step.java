public enum Step {
    Left('L'), Right('R'), Move('M');
    private Step(char valeur){
        this.valeur= valeur;
    }
    private char valeur;
    public char getValeur(){
        return this.valeur;
    }
}
