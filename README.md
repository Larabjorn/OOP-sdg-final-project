<p align="center" dir="auto">
  <img src="https://github.com/Larabjorn/OOP-sdg-final-project/blob/main/image/2.png" width="100">
</p>
<h1 align="center" tabindex="-1" class="heading element" dir="auto">SpendWise Chronicles</h1>

<p align="center" dir="auto">
  <b>IT-2104</b>
  <br>
  <a href="https://github.com/Larabjorn">Lara Bjorn Phillipe L.</a>
</p>

<h2> Table of Contents</h2>
  <ul>
    <li><a href="#project-overview">Project Overview</a></li>
    <li><a href="#oop-principles-and-features">OOP Principles and Features</a></li>
    <li><a href="#chosen-sdg">Chosen SDG</a></li>
    <li><a href="#how-to-run-the-program">How to Run the Program</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ul>
  <hr>

  <h2 id="project-overview"> Project Overview</h2>
  <p><strong>SpendWise Chronicles</strong> is an interactive weekly allowance and expense tracker designed to help users manage finances, optimize spending habits, and achieve savings goals. It provides a simple yet powerful platform to track expenses, analyze budgets, and plan for future savings. Whether you’re budgeting for school, personal needs, or long-term investments, SpendWise empowers users to make smarter financial decisions.</p>
  <hr>

  <h2 id="oop-principles-and-features"> OOP Principles and Features</h2>
  <h3> Encapsulation</h3>
  <ul>
    <li>Private variables store all financial data, such as allowance, expenses, and savings goals. Public getter and setter methods ensure controlled and secure access.</li>
  </ul>
  <h3> Inheritance</h3>
  <ul>
    <li>The parent class <code>FinancialTracker</code> provides shared methods and attributes, while subclasses like <code>AllowanceManager</code> and <code>ExpenseManager</code> extend its functionality.</li>
  </ul>
  <h3> Polymorphism</h3>
  <ul>
    <li>Overloaded methods in <code>calculateSavings()</code> allow for different calculations based on user inputs, such as fixed goals or percentage-based goals.</li>
  </ul>
  <h3> Abstraction</h3>
  <ul>
    <li>The program uses abstract classes and interfaces to define core functionalities like <code>BudgetCalculator</code>, simplifying code reuse and maintenance.</li>
  </ul>
  <hr>
  
<p align="center" dir="auto">
<img src="https://github.com/Larabjorn/OOP-sdg-final-project/blob/main/image/images.png">
</p>

  <h2 id="chosen-sdg"> Chosen SDG</h2>
  <h3> SDG 12: Responsible Consumption and Production</h3>
  <p>
    Goal: Ensure sustainable consumption and production patterns.<br>
    Target: Encourage users to adopt sustainable practices, especially in consumption and production activities.<br>
    Connection: SpendWise motivates users to keep track of expenses and budgeting, potentially influencing more sustainable spending habits by recognizing and reducing wasteful or unnecessary expenses.
  </p>
  <hr>

  <h2 id="how-to-run-the-program"> How to Run the Program</h2>
<p>Follow these steps to run the SpendWise Chronicles program:</p>
<ol>
  <li><b>Download the Code</b>
    <ul>
      <li>Clone or download the repository from GitHub to your local machine using this command:
        <pre><code>git clone https://github.com/Larabjorn/OOP-sdg-final-project.git</code></pre>
      </li>
      <li>Alternatively, you can download the repository as a ZIP file from GitHub, extract it, and open the folder.</li>
    </ul>
  </li>
  <li><b>Compile the Program</b>
    <p>Once you have downloaded the code, navigate to the folder containing the `Expenses.java` file. Open a terminal or command prompt, then use the following command to compile the program:</p>
    <pre><code>javac Expenses.java</code></pre>
    <p>This will generate a compiled class file named <code>Expenses.class</code> in the same folder.</p>
  </li>
  <li><b>Run the Program</b>
    <p>After compiling the program, use the following command to run it:</p>
    <pre><code>java Expenses</code></pre>
    <p>This will launch the graphical user interface (GUI) of the SpendWise Chronicles application, where you can start entering your financial data and track your expenses.</p>
  </li>
  <li>Input Your Allowance and Income</li>
    <p>Select or manually enter your weekly allowance and any additional income. You can choose predefined amounts or enter custom values.</p>

  <li>Enter Expenses</li>
    <p>Enter your weekly expenses in categories like Food, Transport, Entertainment, School, and Miscellaneous. Again, you can choose from predefined values or enter a custom amount.</p>

  <li>Set Your Savings Goal</li>
    <p>Set a savings goal for the week by selecting or entering a custom amount.</p>

  <li>Calculate and View Results</li>
    <p>Click "Calculate" to view:</p>
  <ul>
    <li>Your total income and expenses.</li>
    <li>The remaining allowance or deficit.</li>
    <li>Your emergency fund (5% of allowance) and savings goal progress.</li>
    <li>A budget analysis with recommendations.</li>
    <li>Suggested savings (20% of allowance).</li>
  </ul>

  <li>Reset</h3>
    <p>Click "Reset" to clear all inputs and start fresh.</p>

</ol>
<p>If you encounter any issues, ensure that you have the Java Development Kit (JDK) installed on your system, and that your environment variables (such as <code>PATH</code>) are set up correctly for Java.</p>
<hr>


  <h2 id="acknowledgments"> Acknowledgments</h2>
  <ul>
    <li>Professor Jayson, for guidance throughout the project.</li>
    <li>To helpful YouTube tutorials and online resources.</li>
    <li>To my family and friends for their constant support.</li>
  </ul>
   
