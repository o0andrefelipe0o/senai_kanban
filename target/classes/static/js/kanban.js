function allowDrop(ev) {
  ev.preventDefault();
}
function drag(ev) {
  ev.dataTransfer.setData("text", ev.target.id);
}
function drop(ev) {
  ev.preventDefault();
  const data = ev.dataTransfer.getData("text");
  const task = document.getElementById(data);
  ev.target.closest('.column').appendChild(task);
}
let taskCount = 3;
function createTask() {
  const input = document.getElementById("taskInput");
  const text = input.value.trim();
  if (text === "") return;
  const formattedText = text.replace(/\n/g, "<br>");
  const task = document.createElement("div");
  task.className = "task";
  task.id = `task${taskCount++}`;
  task.draggable = true;
  task.ondragstart = drag;
  task.ondblclick = () => enableEditing(task);
  task.innerHTML = `
    <span class="task-text">${formattedText}</span>
  `;
  document.getElementById("todo").appendChild(task);
  input.value = "";
}
function enableEditing(taskElement) {
    const taskTextElement = taskElement.querySelector('.task-text');
    const originalText = taskTextElement.innerHTML.replace(/<br\s*\/?>/gi, '\n');

    // Cria container de edição
    const editContainer = document.createElement("div");
    editContainer.className = "edit-container";

    // Cria textarea
    const textarea = document.createElement("textarea");
    textarea.value = originalText;
    textarea.className = "edit-textarea";

    // Cria container de botões
    const buttonContainer = document.createElement("div");
    buttonContainer.className = "edit-buttons";

    // Botão Excluir
    const deleteButton = document.createElement("button");
    deleteButton.className = "edit-button edit-delete";
    deleteButton.textContent = "Excluir";
    deleteButton.onclick = () => {
        taskElement.remove();
    };

    // Botão Cancelar
    const cancelButton = document.createElement("button");
    cancelButton.className = "edit-button edit-cancel";
    cancelButton.textContent = "Cancelar";
    cancelButton.onclick = () => {
        taskElement.innerHTML = `
            <span class="task-text">${taskTextElement.innerHTML}</span>
        `;
        restoreTaskFunctionality(taskElement);
    };

    // Botão Salvar
    const saveButton = document.createElement("button");
    saveButton.className = "edit-button edit-save";
    saveButton.textContent = "Salvar";
    saveButton.onclick = () => {
        saveEditedTask(taskElement, textarea.value);
    };

    // Adiciona elementos ao container
    buttonContainer.appendChild(deleteButton);
    buttonContainer.appendChild(cancelButton);
    buttonContainer.appendChild(saveButton);

    editContainer.appendChild(textarea);
    editContainer.appendChild(buttonContainer);

    // Substitui o conteúdo da tarefa
    taskElement.innerHTML = "";
    taskElement.appendChild(editContainer);
    taskElement.draggable = false;

    // Foca no textarea
    textarea.focus();
}

// Função auxiliar para restaurar funcionalidades
function restoreTaskFunctionality(taskElement) {
    taskElement.draggable = true;
    taskElement.ondragstart = drag;
    taskElement.ondblclick = () => enableEditing(taskElement);
}
function saveEditedTask(taskElement, text) {
    const formattedText = text.trim().replace(/\n/g, "<br>");
    if (formattedText === "") {
        taskElement.remove();
        return;
    }
    taskElement.innerHTML = `
        <span class="task-text">${formattedText}</span>
    `;
    restoreTaskFunctionality(taskElement);
}
function restoreTaskFunctionality(taskElement) {
    taskElement.draggable = true;
    taskElement.ondragstart = drag;
    taskElement.ondblclick = () => enableEditing(taskElement);
}
