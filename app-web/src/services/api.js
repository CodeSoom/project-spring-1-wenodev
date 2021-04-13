export async function fetchProblems() {
  const url = "http://localhost:8080/api/v1/problems";
  const response = await fetch(url);
  const data = await response.json();
  return data;
}
